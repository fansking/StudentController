package com.myjpa.springboot.controller;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.AthleteCompetition;
import com.myjpa.springboot.repository.AthleteCompetitionRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/athleteCompetition")
public class AthleteCompetitionController {
    @Autowired
    AthleteCompetitionRepository athleteCompetitionRepository;
    @ApiOperation(value="新建运动员赛事表")
    @PostMapping("/insert")
    public AthleteCompetition insertAthlete(@RequestBody AthleteCompetition athleteCompetition){
        return athleteCompetitionRepository.save(athleteCompetition);
    }
    @ApiOperation(value="找到所有运动员赛事表")
    @PostMapping("")
    public List<AthleteCompetition> findAll(){
        return athleteCompetitionRepository.findAll();
    }
}
