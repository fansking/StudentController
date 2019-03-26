package com.myjpa.springboot.entity;

import com.myjpa.springboot.entity.dbentity.DBLeaders;

import javax.persistence.*;

@Entity
//指定和哪个数据表对应
@Table(name="Leaders")
public class Leader {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    Leader(){
    }

    public Leader(DBLeaders dbLeaders){
        name  = dbLeaders.getName();
        identityNum = dbLeaders.getIdentity_num();
        phoneNum = dbLeaders.getPhone_num();
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
