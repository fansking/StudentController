package com.myjpa.springboot.entity;

import com.myjpa.springboot.entity.dbentity.DBCoaches;

import javax.persistence.*;

@Entity
//指定和哪个数据表对应
@Table(name="Coaches")

public class Coach {
    @Column
    private String name;
    @Id
    @Column
    private String identityNum;
    @Column
    private String phoneNum;

    private Boolean isMale;
    @OneToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="team_id")
    Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    public Coach() {
    }

    public Coach(String name, String identityNum, String phoneNum, Boolean isMale, Team team) {
        this.name = name;
        this.identityNum = identityNum;
        this.phoneNum = phoneNum;
        this.isMale = isMale;
        this.team = team;
    }

    public Coach(DBCoaches dbCoaches){
        name = dbCoaches.getName();
        identityNum = dbCoaches.getIdentity_num();
        phoneNum = dbCoaches.getPhone_num();
        isMale = dbCoaches.getIs_male();
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


    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }
}
