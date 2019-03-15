package com.myjpa.springboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

//使用JPA注解配置映射关系
//告诉JPA这是一个实体类
@Entity
//指定和哪个数据表对应
@Table(name="Athletes")

public class Athlete {
    @Id //主键,运动员号码，由系统自动生成
    @GeneratedValue
    private Integer id;
    @Column
    private String athleteId;

    //年龄
    @Column
    private Integer age;
    @Column
    private String name;
    //身份证号码
    @Column
    private String identityNum;
    @Column
    private String teamName;
    //是否为男性
    @Column
    private Boolean isMale;
    //文化成绩
    @Column
    private Integer scores;
    //参加比赛的项目用逗号隔开，也可不用
    @Column
    private String competitionStr;
    @Transient
    private List<String> competitions;


    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }



    public Athlete() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public String getCompetitionStr() {
        return competitionStr;
    }

    public void setCompetitionStr(String competitionStr) {
        this.competitionStr = competitionStr;
    }

    public List<String> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<String> competitions) {
        this.competitions = competitions;
    }
}
