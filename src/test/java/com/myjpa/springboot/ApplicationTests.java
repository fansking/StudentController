package com.myjpa.springboot;

import com.myjpa.springboot.entity.Athlete;
import com.myjpa.springboot.entity.AthleteCompetition;
import com.myjpa.springboot.entity.Competition;
import com.myjpa.springboot.entity.Team;
import com.myjpa.springboot.repository.AthleteCompetitionRepository;
import com.myjpa.springboot.repository.AthleteRepository;
import com.myjpa.springboot.repository.CompetitionRepository;
import com.myjpa.springboot.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AthleteCompetitionRepository athleteCompetitionRepository;

    @Test
    /*public void init() {
        Athlete athlete1 = new Athlete("001", 12, "张三",
                "123235345", "哈哈哈", false,
                99, "", new HashSet<AthleteCompetition>());
        Athlete athlete2 = new Athlete("002", 12, "老王",
                "123242345345", "哈哈哈", false,
                99, "", new HashSet<AthleteCompetition>());

        Competition competition1 = new Competition("hahah", 12,
                false, true, new HashSet<AthleteCompetition>());


        AthleteCompetition athleteCompetition1 = new AthleteCompetition(athlete1, competition1, 1, 0, 0);
        AthleteCompetition athleteCompetition2 = new AthleteCompetition(athlete2, competition1, 1, 0, 0);


        competition1.getAthleteCompetitions().addAll(Arrays.asList(athleteCompetition2, athleteCompetition1));

        Team team1 = new Team();
        team1.setAthletes(Arrays.asList(athlete1, athlete2));
        athlete1.setTeam(team1);
        athlete2.setTeam(team1);
        team1.setName("asd");
        // teamRepository.save(team1);
//        athlete1.setId(8);
//        athlete2.setId(10);

        athlete1.getAthleteCompetitions().add(athleteCompetition1);
        athlete2.getAthleteCompetitions().add(athleteCompetition2);

        //  List<Team> all = teamRepository.findAll();
        Team asd = teamRepository.findTeamByName("asd");

        competitionRepository.save(competition1);
        athleteRepository.save(athlete1);
        athleteRepository.save(athlete2);
        athleteCompetitionRepository.save(new AthleteCompetition(athlete1, competition1, 99, 1, 1));


    }*/

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public AthleteCompetitionRepository getAthleteCompetitionRepository() {
        return athleteCompetitionRepository;
    }

    public void setAthleteCompetitionRepository(AthleteCompetitionRepository athleteCompetitionRepository) {
        this.athleteCompetitionRepository = athleteCompetitionRepository;
    }
}
