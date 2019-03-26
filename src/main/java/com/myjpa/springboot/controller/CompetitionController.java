package com.myjpa.springboot.controller;


import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.repository.CompetitionRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value="增加或更新多个比赛信息")
    @PostMapping("/insertMany")
    public void insertCompetitions(@RequestBody List<Competition> competitions){
        for (Competition competition:competitions) {
            competitionRepository.save(competition);
        }
    }
    @ApiOperation(value="查找所有比赛信息")
    @GetMapping("/findAll")
    public List<Competition> findAllCompetitions(){
        return competitionRepository.findAll();
    }
}
