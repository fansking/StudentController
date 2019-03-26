package com.myjpa.springboot.service.dbService;

import com.myjpa.springboot.dao.*;
import com.myjpa.springboot.entity.Team;
import com.myjpa.springboot.entity.dbentity.DBTeams;

import java.util.ArrayList;
import java.util.List;

public class DBTeamsService {
    private TeamsDao teamsDao = new TeamsDao();
    private CoachesDao coachesDao = new CoachesDao();
    private DoctorsDao doctorsDao = new DoctorsDao();
    private LeaderDao leaderDao = new LeaderDao();
    private RefereesDao refereesDao = new RefereesDao();
    private AthleteDao athleteDao = new AthleteDao();

    public List<Team> findAll(){
        List<Team> teams = new ArrayList<>();
        List<DBTeams> dbTeams = teamsDao.findAll();
        for(DBTeams dbTeam : dbTeams){
            Team team = new Team(dbTeam);

        }
        return null;
    }
}
