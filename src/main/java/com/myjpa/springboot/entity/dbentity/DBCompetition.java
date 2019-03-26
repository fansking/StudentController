package com.myjpa.springboot.entity.dbentity;

public class DBCompetition {
    private Integer id;
    private Integer age;
    private Boolean is_male;
    private Boolean is_preliminary_contest;
    private String name;

    public DBCompetition() {
    }

    public DBCompetition(Integer id, Integer age, Boolean is_male, Boolean is_preliminary_contest, String name) {
        this.id = id;
        this.age = age;
        this.is_male = is_male;
        this.is_preliminary_contest = is_preliminary_contest;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIs_male() {
        return is_male;
    }

    public void setIs_male(Boolean is_male) {
        this.is_male = is_male;
    }

    public Boolean getIs_preliminary_contest() {
        return is_preliminary_contest;
    }

    public void setIs_preliminary_contest(Boolean is_preliminary_contest) {
        this.is_preliminary_contest = is_preliminary_contest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
