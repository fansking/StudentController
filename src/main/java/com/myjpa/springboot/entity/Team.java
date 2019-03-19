package com.myjpa.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//告诉JPA这是一个实体类
@Entity
//指定和哪个数据表对应
@Table(name = "Teams")
public class Team {
    //队伍名称
    @Column
    private String name;
    //队伍账号
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String account;
    @Column
    private String passWord = "123456";

    //    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
//    @JoinColumn(name="teamId")
//    @JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Athlete> athletes = new ArrayList<>();
    @OneToOne(targetEntity = Leader.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_identityNum")
    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
    private Leader leader;
    @OneToOne(targetEntity = Coach.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_identityNum")
    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
    private Coach coach;
    @OneToOne(targetEntity = Doctor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_identityNum")
    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
    private Doctor doctor;
    @OneToOne(targetEntity = Referee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "referee_identityNum")
    @JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
    private Referee referee;

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }


}


