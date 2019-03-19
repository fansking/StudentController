package com.myjpa.springboot.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//使用JPA注解配置映射关系
//告诉JPA这是一个实体类
@Entity
//指定和哪个数据表对应
public class Athlete implements Serializable {
    @Id
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
    //参加的项目
//    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JoinTable(name="athlete_competition",
//            joinColumns={@JoinColumn(name="aid",referencedColumnName = "id")},
//            inverseJoinColumns={@JoinColumn(name="cid",referencedColumnName = "id")})
//    private Set<Competition> competitions = new HashSet<>();

    //  中间表
//    @OneToMany(targetEntity = AthleteCompetition.class, mappedBy="Athlete", fetch = FetchType.LAZY)
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="athId",referencedColumnName = "id")
    @OneToMany(mappedBy = "athlete", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AthleteCompetition> athleteCompetitions;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }



    public Athlete() {
    }

    public Athlete(String athleteId, Integer age, String name, String identityNum, String teamName, Boolean isMale, Integer scores, String competitionStr, Set<AthleteCompetition> athleteCompetitions) {
        this.athleteId = athleteId;
        this.age = age;
        this.name = name;
        this.identityNum = identityNum;
        this.teamName = teamName;
        this.isMale = isMale;
        this.scores = scores;
        this.competitionStr = competitionStr;
        this.athleteCompetitions = athleteCompetitions;
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

    public Set<AthleteCompetition> getAthleteCompetitions() {
        return athleteCompetitions;
    }

    public void setAthleteCompetitions(Set<AthleteCompetition> athleteCompetitions) {
        this.athleteCompetitions = athleteCompetitions;
    }

    public Integer getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

//    public Set<Competition> getCompetitions() {
//        return competitions;
//    }
//
//    public void setCompetitions(Set<Competition> competitions) {
//        this.competitions = competitions;
//    }
}
