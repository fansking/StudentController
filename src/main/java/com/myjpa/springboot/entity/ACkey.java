package com.myjpa.springboot.entity;

import java.io.Serializable;

public class ACkey implements Serializable {
    Integer athlete;
    Integer competition;

    public Integer getAthlete() {
        return athlete;
    }

    public void setAthlete(Integer athlete) {
        this.athlete = athlete;
    }

    public Integer getCompetition() {
        return competition;
    }

    public void setCompetition(Integer competition) {
        this.competition = competition;
    }

    public ACkey() {
    }
}
