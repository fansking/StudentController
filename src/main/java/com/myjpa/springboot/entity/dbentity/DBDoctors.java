package com.myjpa.springboot.entity.dbentity;

public class DBDoctors {
    private String identity_num;
    private String name;
    private String phone_num;
    private Integer team_id;

    public DBDoctors() {
    }

    public DBDoctors(String identity_num, String name, String phone_num, Integer team_id) {
        this.identity_num = identity_num;
        this.name = name;
        this.phone_num = phone_num;
        this.team_id = team_id;
    }

    public String getIdentity_num() {
        return identity_num;
    }

    public void setIdentity_num(String identity_num) {
        this.identity_num = identity_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }
}
