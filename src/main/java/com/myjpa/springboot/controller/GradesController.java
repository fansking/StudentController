package com.myjpa.springboot.controller;

import com.myjpa.springboot.config.Setting;
import com.myjpa.springboot.dao.GradesDao;
import com.myjpa.springboot.entity.AthleteCompetition;
import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.entity.Grades;
import com.myjpa.springboot.repository.GradesRepository;
import com.myjpa.springboot.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/grades")
public class GradesController {
    @Autowired
    ApiService service;
    @Autowired
    GradesRepository gradesRepository;
    @ApiOperation(value="增加或更新一个裁判裁定的成绩")
    @PostMapping("/insertAll")
    public Boolean insertManyGrades(@RequestBody List<Grades> grades){
        if(Setting.runModel == 1) {
            Boolean flag = true;
            double max = 0;
            double min = grades.get(0).getnGrade();
            double d = 0;
            double p = 0;
            double sum = 0;
            for (Grades grade : grades) {
                gradesRepository.save(grade);
                flag = flag && grade.getPass();
                if (!grade.getReferee().getSir()) {
                    max = max > grade.getnGrade() ? max : grade.getnGrade();
                    min = min < grade.getnGrade() ? min : grade.getnGrade();
                    sum += grade.getnGrade();
                } else {
                    d = grade.getdGrade();
                    p = grade.getpGrade();
                }
            }
            if (flag) {
                AthleteCompetition ac = grades.get(0).getAthleteCompetition();
                sum = sum - max - min;

                ac.setScore(sum / (grades.size() - 3) * (grades.size() - 1) + d - p);
                if (ac.getScore() < 0) {
                    return false;
                }
                service.saveAC(ac);
            }
            return true;
        } else {
            GradesDao gradesDao = new GradesDao();
            String sql = "select (sum(n_grade)-max(n_grade)-min(n_grade))/(count(*)-3)*(count(*)-1)+sum(d_grade)-sum(p_grade) score" +
                    ",athlete_id,competition_id from grades group by athlete_id,competition_id order by score desc";
            List<HashMap<String, Object>> resultMap = gradesDao.getResultMap(sql);
            String sql2 = "update athlete_competition set score = ? where athlete_id = ? and competition_id = ?";
            for(HashMap<String,Object> tmp : resultMap){
                gradesDao.update(sql2,tmp.get("score"),tmp.get("athlete_id"),tmp.get("competition_id"));
            }
            return true;
        }
    }
    @ApiOperation(value="增加或更新一个裁判裁定的成绩")
    @PostMapping("/insert")
    public Grades insertGrades(@RequestBody Grades grades){
        return gradesRepository.save(grades);
    }
    @ApiOperation(value="")
    @GetMapping("/find")
    public List<Grades> findAll(){
        return gradesRepository.findAll();
    }
    @ApiOperation(value="")
    @GetMapping("/autoCalculate")
    public Boolean autoCalculate(){
        if(Setting.runModel == 1) {
            List<Competition> competitions = service.findAllCom();
            for (Competition competition : competitions) {
                List<AthleteCompetition> athleteCompetitions = service.findByCompetition_IdOrderByScoreDesc(competition.getId());
                for (int i = 0; i < athleteCompetitions.size(); i++) {
                    athleteCompetitions.get(i).setAthleteRank(i + 1);
                    if (athleteCompetitions.get(i).getScore() < 0) {
                        return false;
                    }
                    service.saveAC(athleteCompetitions.get(i));
                }
            }
            return true;
        } else {
            GradesDao gradesDao = new GradesDao();
            String sql1 = "select id from competition";
            List<HashMap<String, Object>> competitionIDMapList = gradesDao.getResultMap(sql1);
            for(HashMap<String,Object> competitionIDMap : competitionIDMapList){
                String sql2 = "select athlete_id from athlete_competition where competition_id = ? order by score desc";
                List<HashMap<String, Object>> athleteIDList = gradesDao.getResultMap(sql2, competitionIDMap.get("id"));
                int rank = 1;
                for(HashMap<String,Object> athleteID : athleteIDList){
                    String sql3 = "update athlete_competition set athlete_rank = ? where athlete_id = ? and competition_id = ?";
                    gradesDao.update(sql3,rank,athleteID.get("athlete_id"),competitionIDMap.get("id"));
                    rank++;
                }
            }
            return true;
        }
    }
    @ApiOperation(value="")
    @GetMapping("/findByRefereeIdentityNum/{refereeId}")
    public List<Grades> findGradesByRefereeIdentityNum(@PathVariable String refereeId){
        return gradesRepository.findByReferee_IdentityNum(refereeId);
    }
    @ApiOperation(value="")
    @GetMapping("/findByAthleteCompetition_Id/{competitionId}")
    public List<Grades> findGradesByAthleteCompetition_Id(@PathVariable Integer competitionId){
        return gradesRepository.findByCompetition_Id(competitionId);
    }
    @ApiOperation(value="")
    @GetMapping("/find/{athleteId}/{competitionId}")
    public List<Grades> findAllByAthlete_IdAndCompetition_Id(@PathVariable Integer athleteId, @PathVariable Integer competitionId){
        return gradesRepository.findByAthlete_IdAndCompetition_Id(athleteId,competitionId);
    }
    @ApiOperation(value="")
    @GetMapping("/find/{refereeId}/{athleteId}/{competitionId}")
    public List<Grades> findGradesByRefereeIdentityNumAndAthleteCompetition_Id(@PathVariable String refereeId,@PathVariable Integer athleteId, @PathVariable Integer competitionId){
        return gradesRepository.findByReferee_IdentityNumAndAthlete_IdAndCompetition_Id(refereeId,athleteId,competitionId);
    }

    @GetMapping("/allRank")
    public Object findAllRank(){
        return service.findAllRank();
    }
}
