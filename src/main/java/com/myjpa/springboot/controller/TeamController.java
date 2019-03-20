package com.myjpa.springboot.controller;

import com.myjpa.springboot.entity.*;
import com.myjpa.springboot.repository.TeamRepository;
import com.myjpa.springboot.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "team")
public class TeamController {
    @Autowired
    ApiService service;
    @Autowired
    TeamRepository teamRepository;
    @ApiOperation(value="增加或更新一个队伍信息")
    @PostMapping("/insert")
    public Team insertAthlete(@RequestBody Team team){
        return teamRepository.save(team);
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
        return teamRepository.findAll();
    }
    @ApiOperation(value="新增教练")
    @PostMapping("coach")
    public Coach coach(@RequestBody Coach coach){
        return service.insertCoach(coach);
    }
    @ApiOperation(value="新增领队")
    @PostMapping("leader")
    public Leader leader(@RequestBody Leader leader){
        return service.insertLeader(leader);
    }
    @ApiOperation(value="新增队医")
    @PostMapping("doctor")
    public Doctor doctor(@RequestBody Doctor doctor){
        return service.insertDoctor(doctor);
    }
    @ApiOperation(value="新增裁判")
    @PostMapping("referee")
    public Referee referee(@RequestBody Referee referee){
        return service.insertReferee(referee);
    }
    @ApiOperation(value="新增所有人员")
    @PostMapping("all")
    public void referee(@RequestBody Teamrequest re){
         service.addTeamMetaData(re.getAthletes(),re.getLeader(),re.getCoach(),re.getDoctor(),re.getReferee());
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