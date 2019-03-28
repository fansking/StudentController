package com.myjpa.springboot.controller;

import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.entity.Grades;
import com.myjpa.springboot.repository.GradesRepository;
import com.myjpa.springboot.service.ApiService;
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
    public void insertManyGrades(@RequestBody List<Grades> grades){
        for (Grades grade:grades
             ) {
            gradesRepository.save(grade);
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
