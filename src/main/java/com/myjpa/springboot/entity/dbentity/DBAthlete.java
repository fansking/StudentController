package com.myjpa.springboot.entity.dbentity;

import com.myjpa.springboot.entity.Athlete;

public class DBAthlete {
    private Integer id;
    private Integer age;
    private String athlete_id;
    private String competition_str;
    private String identity_num;
    private Boolean is_male;
    private String name;
    private Integer scores;
    private String team_name;
    private Integer team_id;

    public DBAthlete() {
    }

    public DBAthlete(Athlete athlete){
        this.id = athlete.getId();
        this.age = athlete.getAge();
        this.athlete_id = athlete.getAthleteId();
        this.competition_str = athlete.getCompetitionStr();
        this.identity_num = athlete.getIdentityNum();
        this.is_male = athlete.getMale();
        this.name = athlete.getName();
        this.scores = athlete.getScores();
        this.team_name = athlete.getTeamName();
        this.team_id = athlete.getTeam().getId();
    }

    public DBAthlete(Integer id, Integer age, String athlete_id, String competition_str,
                     String identity_num, Boolean is_male, String name, Integer scores,
                     String team_name, Integer team_id) {
        this.id = id;
        this.age = age;
        this.athlete_id = athlete_id;
        this.competition_str = competition_str;
        this.identity_num = identity_num;
        this.is_male = is_male;
        this.name = name;
        this.scores = scores;
        this.team_name = team_name;
        this.team_id = team_id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(String athlete_id) {
        this.athlete_id = athlete_id;
    }

    public String getCompetition_str() {
        return competition_str;
    }

    public void setCompetition_str(String competition_str) {
        this.competition_str = competition_str;
    }

    public String getIdentity_num() {
        return identity_num;
    }

    public void setIdentity_num(String identity_num) {
        this.identity_num = identity_num;
    }

    public Boolean getIs_male() {
        return is_male;
    }

    public void setIs_male(Boolean is_male) {
        this.is_male = is_male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }
}
