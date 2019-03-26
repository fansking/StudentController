package com.myjpa.springboot.entity;

import com.myjpa.springboot.entity.dbentity.DBGrades;

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
    Double nGrade;
    Double dGrade;
    Double pGrade;
    Boolean isPass;

    public Grades() {
    }

    public Grades(DBGrades dbGrades){
        id = dbGrades.getId();
        nGrade = dbGrades.getN_grade();
        dGrade = dbGrades.getD_grade();
        pGrade = dbGrades.getP_grade();
        isPass = dbGrades.getIs_pass();
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

    public Double getnGrade() {
        return nGrade;
    }

    public void setnGrade(Double nGrade) {
        this.nGrade = nGrade;
    }

    public Double getdGrade() {
        return dGrade;
    }

    public void setdGrade(Double dGrade) {
        this.dGrade = dGrade;
    }

    public Double getpGrade() {
        return pGrade;
    }

    public void setpGrade(Double pGrade) {
        this.pGrade = pGrade;
    }

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }
}
