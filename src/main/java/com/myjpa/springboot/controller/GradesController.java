package com.myjpa.springboot.controller;

import com.myjpa.springboot.entity.AthleteCompetition;
import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.entity.Grades;
import com.myjpa.springboot.repository.GradesRepository;
import com.myjpa.springboot.service.ApiService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Boolean flag=true;
        double max=0;
        double min=grades.get(0).getnGrade();
        double d=0;
        double p=0;
        double sum=0;
        for (Grades grade:grades) {
            gradesRepository.save(grade);
            flag=flag&&grade.getPass();
            if(!grade.getReferee().getSir()){
                max=max>grade.getnGrade()?max:grade.getnGrade();
                min=min<grade.getnGrade()?min:grade.getnGrade();
                sum+=grade.getnGrade();
            }else{
                d=grade.getdGrade();
                p=grade.getpGrade();
            }
        }
        if(flag){
            AthleteCompetition ac=grades.get(0).getAthleteCompetition();
            sum=sum-max-min;

            ac.setScore(sum/(grades.size()-3)*(grades.size()-1)+d-p);
            if(ac.getScore()<0){
                return false;
            }
            service.saveAC(ac);
        }
        return true;
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
        List<Competition> competitions=service.findAllCom();
        for(Competition competition:competitions){
            List<AthleteCompetition> athleteCompetitions=service.findByCompetition_IdOrderByScoreDesc(competition.getId())  ;
            for(int i=0;i<athleteCompetitions.size();i++){
                athleteCompetitions.get(i).setAthleteRank(i+1);
                if(athleteCompetitions.get(i).getScore()<0){
                    return false;
                }
                service.saveAC(athleteCompetitions.get(i));
            }

        }
        return true;
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
}
