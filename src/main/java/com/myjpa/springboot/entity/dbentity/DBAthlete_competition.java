package com.myjpa.springboot.entity.dbentity;

import com.myjpa.springboot.entity.AthleteCompetition;

public class DBAthlete_competition {
    private Integer athlete_id;
    private Integer competition_id;
    private Integer athlete_rank;
    private Integer group_num;
    private Double score;

    public DBAthlete_competition() {
    }

    public DBAthlete_competition(AthleteCompetition athleteCompetition){
        athlete_id = athleteCompetition.getAthlete().getId();
        competition_id = athleteCompetition.getCompetition().getId();
        athlete_rank = athleteCompetition.getAthleteRank();
        group_num = athleteCompetition.getGroupNum();
        score = athleteCompetition.getScore();
    }

    public DBAthlete_competition(Integer athlete_id, Integer competition_id, Integer athlete_rank, Integer group_num, Double score) {
        this.athlete_id = athlete_id;
        this.competition_id = competition_id;
        this.athlete_rank = athlete_rank;
        this.group_num = group_num;
        this.score = score;
    }

    public Integer getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(Integer athlete_id) {
        this.athlete_id = athlete_id;
    }

    public Integer getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(Integer competition_id) {
        this.competition_id = competition_id;
    }

    public Integer getAthlete_rank() {
        return athlete_rank;
    }

    public void setAthlete_rank(Integer athlete_rank) {
        this.athlete_rank = athlete_rank;
    }

    public Integer getGroup_num() {
        return group_num;
    }

    public void setGroup_num(Integer group_num) {
        this.group_num = group_num;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
