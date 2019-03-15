package com.myjpa.springboot.controller;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.Team;
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
        Team save=teamRepository.save(team);
        return save;
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


}
