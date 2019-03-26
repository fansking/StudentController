package com.myjpa.springboot.entity.dbentity;

public class DBAthlete_competition {
    private Integer athlete_id;
    private Integer competition_id;
    private Integer athlete_rank;
    private Integer group_num;
    private Integer score;

    public DBAthlete_competition() {
    }

    public DBAthlete_competition(Integer athlete_id, Integer competition_id, Integer athlete_rank, Integer group_num, Integer score) {
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
