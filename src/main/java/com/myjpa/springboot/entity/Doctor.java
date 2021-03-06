package com.myjpa.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myjpa.springboot.entity.dbentity.DBDoctors;

import javax.persistence.*;

@Entity
//指定和哪个数据表对应
@Table(name="Doctors")

public class Doctor {
    @Column
    private String name;
    @Id
    @Column
    private String identityNum;
    @Column
    private String phoneNum;
    @OneToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="team_id")
    Team team;

    public Doctor(DBDoctors dbDoctors) {
        name = dbDoctors.getName();
        identityNum = dbDoctors.getIdentity_num();
        phoneNum = dbDoctors.getPhone_num();
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Doctor() {
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
