package com.myjpa.springboot.controller;


import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.repository.CompetitionRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "competition")
public class CompetitionController {
    @Autowired
    CompetitionRepository competitionRepository;

    @ApiOperation(value="增加或更新一个比赛信息")
    @PostMapping("/insert")
    public Competition insertAthlete(@RequestBody Competition competition){
        return competitionRepository.save(competition);
    }
}
