package com.myjpa.springboot.entity;

import javax.persistence.*;

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
    @OneToOne(mappedBy = "referee")
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
