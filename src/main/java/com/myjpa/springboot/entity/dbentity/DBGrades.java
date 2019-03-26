package com.myjpa.springboot.entity.dbentity;

public class DBGrades {
    private Integer id;
    private Integer d_grade;
    private Boolean is_pass;
    private Integer n_grade;
    private Integer p_grade;
    private Integer athlete_id;
    private Integer competition_id;
    private String referee_identity_num;

    public DBGrades(Integer id, Integer d_grade, Boolean is_pass, Integer n_grade, Integer p_grade, Integer athlete_id, Integer competition_id, String referee_identity_num) {
        this.id = id;
        this.d_grade = d_grade;
        this.is_pass = is_pass;
        this.n_grade = n_grade;
        this.p_grade = p_grade;
        this.athlete_id = athlete_id;
        this.competition_id = competition_id;
        this.referee_identity_num = referee_identity_num;
    }

    public DBGrades() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getD_grade() {
        return d_grade;
    }

    public void setD_grade(Integer d_grade) {
        this.d_grade = d_grade;
    }

    public Boolean getIs_pass() {
        return is_pass;
    }

    public void setIs_pass(Boolean is_pass) {
        this.is_pass = is_pass;
    }

    public Integer getN_grade() {
        return n_grade;
    }

    public void setN_grade(Integer n_grade) {
        this.n_grade = n_grade;
    }

    public Integer getP_grade() {
        return p_grade;
    }

    public void setP_grade(Integer p_grade) {
        this.p_grade = p_grade;
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

    public String getReferee_identity_num() {
        return referee_identity_num;
    }

    public void setReferee_identity_num(String referee_identity_num) {
        this.referee_identity_num = referee_identity_num;
    }
}
