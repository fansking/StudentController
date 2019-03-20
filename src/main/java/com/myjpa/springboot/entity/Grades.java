package com.myjpa.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Grades implements Serializable {
    /*
    由三个外键构成对应哪个运动员哪场比赛的哪个裁判的评分结果
     */
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne(targetEntity = AthleteCompetition.class, fetch= FetchType.EAGER)
    @JoinColumns({
            // 分别匹配主表联合主键的两个字段
            @JoinColumn(name="competition_id", referencedColumnName="competition_id"),
            @JoinColumn(name="athlete_id", referencedColumnName="athlete_id")
    })
    private AthleteCompetition athleteCompetition;
    @ManyToOne(targetEntity = Referee.class,fetch=FetchType.EAGER)
    @JoinColumn(name = "referee_identityNum")
    private Referee referee;
    Integer nGrade;
    Integer dGrade;
    Integer pGrade;
    Boolean isPass;

    public Grades() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AthleteCompetition getAthleteCompetition() {
        return athleteCompetition;
    }

    public void setAthleteCompetition(AthleteCompetition athleteCompetition) {
        this.athleteCompetition = athleteCompetition;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public Integer getnGrade() {
        return nGrade;
    }

    public void setnGrade(Integer nGrade) {
        this.nGrade = nGrade;
    }

    public Integer getdGrade() {
        return dGrade;
    }

    public void setdGrade(Integer dGrade) {
        this.dGrade = dGrade;
    }

    public Integer getpGrade() {
        return pGrade;
    }

    public void setpGrade(Integer pGrade) {
        this.pGrade = pGrade;
    }

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }
}
