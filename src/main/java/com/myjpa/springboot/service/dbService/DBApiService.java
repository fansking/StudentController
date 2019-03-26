package com.myjpa.springboot.service.dbService;

import com.myjpa.springboot.dao.*;
import com.myjpa.springboot.entity.*;
import com.myjpa.springboot.entity.dbentity.*;

import java.util.List;

public class DBApiService {
    private AthleteDao athleteDao = new AthleteDao();
    private LeaderDao leaderDao = new LeaderDao();
    private CoachesDao coachesDao = new CoachesDao();
    private DoctorsDao doctorsDao = new DoctorsDao();
    private RefereesDao refereesDao = new RefereesDao();

    public void addTeamMetaData(List<Athlete> athletes, Leader leader,
                                Coach coach, Doctor doctor, Referee referee) {
        for (Athlete athlete : athletes){
            athleteDao.save(new DBAthlete(athlete));
        }
        leaderDao.save(new DBLeaders(leader));
        coachesDao.save(new DBCoaches(coach));
        doctorsDao.save(new DBDoctors(doctor));
        refereesDao.save(new DBReferees(referee));
    }
}
