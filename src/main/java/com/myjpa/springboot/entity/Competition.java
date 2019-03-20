package com.myjpa.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Competition implements Serializable {
    /**
     * 系统自动生成的主键
     */
    @Id
    @GeneratedValue
    private Integer id;


    /**
     * 项目名称
     */
    private String name;


    /**
     * 年龄组别
     */
    private Integer age;


    /**
     * 性别组
     */
    private boolean isMale;


    /**
     * 阶段(初赛or决赛)
     */
    private boolean isPreliminaryContest;

    /**
     * 对应的运动员，多对多关系
     */
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="athlete_competition",
//            joinColumns={@JoinColumn(name="cid",referencedColumnName = "id")},
//            inverseJoinColumns={@JoinColumn(name="aid",referencedColumnName = "id")}
//            )
//    private Set<Athlete> athletes = new HashSet<>();


    /**
     * 比赛和运动员的中间表，一对多
     */
   // @OneToMany(targetEntity = AthleteCompetition.class, mappedBy="Competition", fetch = FetchType.LAZY)
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="competitionId",referencedColumnName = "id")
    @OneToMany(mappedBy = "competition",fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<AthleteCompetition> athleteCompetitions;

    public Competition(String name, Integer age, boolean isMale, boolean isPreliminaryContest, Set<AthleteCompetition> athleteCompetitions) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.isPreliminaryContest = isPreliminaryContest;
        this.athleteCompetitions = athleteCompetitions;
    }

    public Competition() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isPreliminaryContest() {
        return isPreliminaryContest;
    }

    public void setPreliminaryContest(boolean preliminaryContest) {
        isPreliminaryContest = preliminaryContest;
    }

    public Set<AthleteCompetition> getAthleteCompetitions() {
        return athleteCompetitions;
    }

    public void setAthleteCompetitions(Set<AthleteCompetition> athleteCompetitions) {
        this.athleteCompetitions = athleteCompetitions;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isMale=" + isMale +
                ", isPreliminaryContest=" + isPreliminaryContest +
                ", athleteCompetitions=" + athleteCompetitions +
                '}';
    }
    //    public Set<Athlete> getAthletes() {
//        return athletes;
//    }
//
//    public void setAthletes(Set<Athlete> athletes) {
//        this.athletes = athletes;
//    }
}
