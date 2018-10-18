package com.myjpa.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
//告诉JPA这是一个实体类
@Entity
//指定和哪个数据表对应
@Table(name="student")
public class Student {
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private int id;
    @Column
    private int age;
    @Column
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
