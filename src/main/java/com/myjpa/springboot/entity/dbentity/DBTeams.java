package com.myjpa.springboot.entity.dbentity;

public class DBTeams {
    private Integer id;
    private String account;
    private String name;
    private String pass_word;

    public DBTeams(Integer id, String account, String name, String pass_word) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.pass_word = pass_word;
    }

    public DBTeams() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }
}
