package com.myjpa.springboot.entity;

import sun.plugin2.message.Serializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//告诉JPA这是一个实体类
@Entity
//指定和哪个数据表对应
@Table(name="Teams")
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
    private String passWord="123456";
    @OneToMany(cascade = CascadeType.ALL)
    List<Athlete> athletes = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leader_identityNum")
    private Leader leader;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_identityNum")
    private Coach coach;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_identityNum")
    private Doctor doctor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referee_identityNum")
    private Doctor referee;

    public Doctor getReferee() {
        return referee;
    }

    public void setReferee(Doctor referee) {
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


