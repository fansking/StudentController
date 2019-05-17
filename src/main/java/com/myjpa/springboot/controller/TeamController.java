package com.myjpa.springboot.controller;

import com.myjpa.springboot.config.Setting;
import com.myjpa.springboot.config.Utils;
import com.myjpa.springboot.dao.*;
import com.myjpa.springboot.entity.*;
import com.myjpa.springboot.entity.dbentity.DBCoaches;
import com.myjpa.springboot.entity.dbentity.DBDoctors;
import com.myjpa.springboot.entity.dbentity.DBLeaders;
import com.myjpa.springboot.entity.dbentity.DBReferees;
import com.myjpa.springboot.repository.TeamRepository;
import com.myjpa.springboot.service.ApiService;
import com.myjpa.springboot.service.dbService.DBApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "team")
public class TeamController {
    DBApiService dbApiService = new DBApiService();
    TeamsDao teamsDao = new TeamsDao();
    @Autowired
    ApiService service;
    @Autowired
    TeamRepository teamRepository;
    @ApiOperation(value="增加或更新一个队伍信息")
    @PostMapping("/insert")
    public Team insertTeam(@RequestBody Team team){
        return teamRepository.save(team);
    }
    @ApiOperation(value="增加或更新一个队伍信息")
    @PostMapping("/insertMany")
    public void insertTeams(@RequestBody List<Team> teams){
        for (Team team:teams) {
            teamRepository.save(team);
        }

    }
    @ApiOperation(value="通过队名查找一个队伍")
    @GetMapping("/teamName/{name}")
    public Team findTeamByName(@PathVariable String name){

        return teamRepository.findTeamByName(name);
    }
    @ApiOperation(value="通过账号查找一个队伍")
    @GetMapping("/teamAccount/{account}")
    public Team findTeamByAccount(@PathVariable String account){

        return teamRepository.findTeamByAccount(account);
    }
    @ApiOperation(value="查找所有队伍")
    @GetMapping("")
    public List<Team> findTeams(){
        if(Setting.runModel == 1){
            return teamRepository.findAll();
        }
        else {
            return Team.getListTeam(teamsDao.findAll());
        }
    }
    @ApiOperation(value="查找所有裁判")
    @GetMapping("/referee")
    public List<Referee> findReferees(){
        return service.findAllReferee();
    }
    @ApiOperation(value="新增教练")
    @PostMapping("/coach")
    public Coach coach(@RequestBody Coach coach){
        return service.insertCoach(coach);
    }
    @ApiOperation(value="新增领队")
    @PostMapping("/leader")
    public Leader leader(@RequestBody Leader leader){
        return service.insertLeader(leader);
    }
    @ApiOperation(value="新增队医")
    @PostMapping("/doctor")
    public Doctor doctor(@RequestBody Doctor doctor){
        return service.insertDoctor(doctor);
    }
    @ApiOperation(value="新增裁判")
    @PostMapping("/referee")
    public Referee referee(@RequestBody Referee referee){
        return service.insertReferee(referee);
    }
    @ApiOperation(value="新增所有人员")
    @PostMapping("/all")
    public void addALL(@RequestBody Teamrequest re){
        if(Setting.runModel == 1) {
            service.addTeamMetaData(re.getAthletes(), re.getLeader(), re.getCoach(), re.getDoctor(), re.getReferee());
        }else {
            dbApiService.addTeamMetaData(re.getAthletes(), re.getLeader(), re.getCoach(), re.getDoctor(), re.getReferee());
        }
    }

    @GetMapping("/findByTeamID/{teamID}")
    public Teamrequest findAllByTeamID(@PathVariable Integer teamID){
        Teamrequest teamrequest = new Teamrequest();
        if(Setting.runModel == 0){
        } else {
            teamrequest.setAthletes(Athlete.getListAthlete(new AthleteDao().findByTeam_id(teamID)));
            Object[] objects = new CoachesDao().findByTeam_id(teamID).toArray();
            List<Object> objects1 = Arrays.asList(objects);
            teamrequest.setCoach(new Coach((DBCoaches)Utils.getFirstOneFromList(Arrays.asList(new CoachesDao().findByTeam_id(teamID).toArray()))));
            teamrequest.setDoctor(new Doctor((DBDoctors)Utils.getFirstOneFromList(Arrays.asList(new DoctorsDao().findByTeam_id(teamID).toArray()))));
            teamrequest.setLeader(new Leader((DBLeaders)Utils.getFirstOneFromList(Arrays.asList(new LeaderDao().findByTeam_id(teamID).toArray()))));
            teamrequest.setReferee(new Referee((DBReferees)Utils.getFirstOneFromList(Arrays.asList(new RefereesDao().findByTeam_id(teamID).toArray()))));
        }
        return teamrequest;
    }
}
class Teamrequest{
    List<Athlete> athletes;
    Leader leader;
    Coach coach;
    Doctor doctor;
    Referee referee;

    public Teamrequest() {
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }
}