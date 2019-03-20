package com.myjpa.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@IdClass(ACkey.class)
public class AthleteCompetition implements Serializable {


    @Id
    @ManyToOne(targetEntity = Competition.class,fetch=FetchType.EAGER)
    private Competition competition;
    @Id
    @ManyToOne(targetEntity = Athlete.class,fetch=FetchType.EAGER)
    private Athlete athlete;
    @OneToMany(mappedBy ="athleteCompetition" ,targetEntity = Grades.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Grades> grades;

   /* @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "athleteCompetition")
    @JsonIgnore
    Set<Referee> referees;*/

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public AthleteCompetition(Athlete athlete, Competition competition, int groupNum, double score, int athleteRank) {
        this.athlete = athlete;
        this.competition = competition;
        this.groupNum = groupNum;
        this.score = score;
        this.athleteRank = athleteRank;
    }

    public AthleteCompetition() {
    }

    /**
     * 参加的小组
     */
    private int groupNum;


    /**
     * 获得的成绩
     */
    private double score;


    /**
     * 名次
     */
    private int athleteRank;


    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAthleteRank() {
        return athleteRank;
    }

    public void setAthleteRank(int athleteRank) {
        this.athleteRank = athleteRank;
    }
}
