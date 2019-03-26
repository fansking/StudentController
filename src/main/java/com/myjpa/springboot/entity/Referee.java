package com.myjpa.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myjpa.springboot.entity.dbentity.DBReferees;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//指定和哪个数据表对应
@Table(name="Referees")
public class Referee {
    @Column
    private String name;
    @Id
    @Column
    private String identityNum;
    @Column
    private String phoneNum;
    private Boolean sir;
    @OneToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="team_id")
    Team team;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "referee")
    @JsonIgnore
    List<Grades> grades = new ArrayList<>();

    public Referee(DBReferees dbReferees){
        name = dbReferees.getName();
        identityNum = dbReferees.getIdentity_num();
        phoneNum = dbReferees.getPhone_num();
        sir = dbReferees.getSir();
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public Boolean getSir() {
        return sir;
    }

    public void setSir(Boolean sir) {
        this.sir = sir;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Referee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
