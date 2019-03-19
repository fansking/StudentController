package com.myjpa.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AthleteCompetition implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="aid")*/

    @ManyToOne
    @JoinColumn(name="competition_id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

  /*  @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="cid")*/


    public AthleteCompetition(Athlete athlete, Competition competition, int groupNum, double score, int rank) {
        this.athlete = athlete;
        this.competition = competition;
        this.groupNum = groupNum;
        this.score = score;
        this.rank = rank;
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
    private int rank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  /*  public Athlete getAthlete() {
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
    }*/

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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
