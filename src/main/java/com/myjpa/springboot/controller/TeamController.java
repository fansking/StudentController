package com.myjpa.springboot.controller;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.Team;
import com.myjpa.springboot.repository.TeamRepository;
import com.myjpa.springboot.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "Team")
public class TeamController {
    @Autowired
    ApiService service;
    @Autowired
    TeamRepository teamRepository;
    @ApiOperation(value="增加一个队伍")
    @PostMapping("/insert")
    public Team insertAthlete(@RequestBody Team team){
        Team save=teamRepository.save(team);
        return save;
    }

}
