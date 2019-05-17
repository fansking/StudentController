package com.myjpa.springboot.service;

import com.myjpa.springboot.entity.*;
import com.myjpa.springboot.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiService implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    AthleteRepository athleteRepository;
    @Autowired
    AthleteCompetitionRepository athleteCompetitionRepository;
    @Autowired
    CoachRepository coachRepository;
    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    LeaderRepository leaderRepository;
    @Autowired
    RefereeRepository refereeRepository;
    @Autowired
    GradesRepository gradesRepository;

    public void addTeamMetaData(List<Athlete> athletes, Leader leader, Coach coach, Doctor doctor,Referee referee){
        for (Athlete athlete:athletes) {
            athleteRepository.save(athlete);
        }
        leaderRepository.save(leader);
        coachRepository.save(coach);
        doctorRepository.save(doctor);
        refereeRepository.save(referee);

    }
    public List<Competition> findAllCom(){
        return competitionRepository.findAll();
    }
    public List<AthleteCompetition> findByCompetition_IdOrderByScoreDesc(Integer id){
        return athleteCompetitionRepository.findByCompetition_IdOrderByScoreDesc(id);
    }
    public void saveAC(AthleteCompetition athleteCompetition){
        athleteCompetitionRepository.save(athleteCompetition);
    }
    public  List<Referee> findAllReferee(){
        return refereeRepository.findAll();
    }
    public Coach insertCoach(Coach coach){
        return coachRepository.save(coach);
    }
    public Leader insertLeader(Leader leader){
        return leaderRepository.save(leader);
    }
    public Doctor insertDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    public Referee insertReferee(Referee referee){
        return refereeRepository.save(referee);
    }
    public Object findAllRank () {
        List<Object> result = new ArrayList<>();
        List<Competition> competitionList = competitionRepository.findAll();
        for (Competition competition : competitionList) {
            List<AthleteCompetition> athleteCompetitionList = athleteCompetitionRepository.findByCompetition_IdOrderByScoreDesc(competition.getId());
            if (athleteCompetitionList != null && athleteCompetitionList.size() > 0) {
                HashMap<String, Object> competitionData = new HashMap<>();
                competitionData.put("competitionName", competition.getName());
                List<Object> rankDataList = new ArrayList<>();
                for (AthleteCompetition athleteCompetition : athleteCompetitionList) {
                    HashMap<String, Object> lineData = new HashMap<>();
                    lineData.put("athleteName", athleteCompetition.getAthlete().getName());
                    lineData.put("teamName", athleteCompetition.getAthlete().getTeam().getName());
                    lineData.put("score", athleteCompetition.getScore());
                    lineData.put("pScore", gradesRepository.findByAthlete_IdAndCompetition_IdAndDGradeNotNull(athleteCompetition.getAthlete().getId(), athleteCompetition.getCompetition().getId()).get(0).getpGrade());
                    lineData.put("dScore", gradesRepository.findByAthlete_IdAndCompetition_IdAndDGradeNotNull(athleteCompetition.getAthlete().getId(), athleteCompetition.getCompetition().getId()).get(0).getdGrade());
                    lineData.put("rank", athleteCompetition.getAthleteRank());
                    rankDataList.add(lineData);
                }
                competitionData.put("data", rankDataList);

                result.add(competitionData);
            }
        }
        return result;
    }
}
