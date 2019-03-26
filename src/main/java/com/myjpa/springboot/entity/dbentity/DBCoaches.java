package com.myjpa.springboot.entity.dbentity;

public class DBCoaches {
    private String identity_num;
    private Boolean is_male;
    private String name;
    private String phone_num;
    private Integer team_id;

    public DBCoaches() {
    }

    public DBCoaches(String identity_num, Boolean is_male, String name, String phone_num, Integer team_id) {
        this.identity_num = identity_num;
        this.is_male = is_male;
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

    public Boolean getIs_male() {
        return is_male;
    }

    public void setIs_male(Boolean is_male) {
        this.is_male = is_male;
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
