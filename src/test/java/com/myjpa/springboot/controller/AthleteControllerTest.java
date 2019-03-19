package com.myjpa.springboot.controller;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.AthleteCompetition;
import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.repository.AthleteRepository;
import com.myjpa.springboot.repository.CompetitionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringRunner.class)
public class AthleteControllerTest {

    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private CompetitionRepository competitionRepository;

    @Before
    public void init() {
        Athlete athlete1 = new Athlete("001",12,"张三",
                "123235345","哈哈哈",false,
                99,"",new HashSet<AthleteCompetition>());
        Athlete athlete2 = new Athlete("002",12,"老王",
                "123242345345","哈哈哈",false,
                99,"",new HashSet<AthleteCompetition>());

        Competition competition1 = new Competition("hahah",12,
                false,true,new HashSet<AthleteCompetition>());

        AthleteCompetition athleteCompetition1 = new AthleteCompetition(athlete1,competition1,1,0,0);
        AthleteCompetition athleteCompetition2 = new AthleteCompetition(athlete2,competition1,1,0,0);

        athlete1.getAthleteCompetitions().add(athleteCompetition1);
        athlete2.getAthleteCompetitions().add(athleteCompetition2);

        competition1.getAthleteCompetitions().addAll(Arrays.asList(athleteCompetition2,athleteCompetition1));

        competitionRepository.save(competition1);
        athleteRepository.save(athlete1);
        athleteRepository.save(athlete2);
    }

    @Test
    public void test1(){
        System.out.println("aaaa");
    }


}