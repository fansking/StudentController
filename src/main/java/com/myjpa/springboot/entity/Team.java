package com.myjpa.springboot.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//告诉JPA这是一个实体类
@Entity
//指定和哪个数据表对应
@Table(name="Teams")
public class Team {
    //队伍名称
    @Column
    private String name;
    //队伍账号
    @Column
    private String account;
    @Column
    private String passWord;
    @OneToMany(cascade = CascadeType.ALL)
    List<Athlete> athletes = new ArrayList<>();

}
