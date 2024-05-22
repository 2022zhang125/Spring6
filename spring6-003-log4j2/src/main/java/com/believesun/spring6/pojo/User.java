package com.believesun.spring6.pojo;

public class User {
    private String name;
    private Integer id;
    private Character sex;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", sex=" + sex +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public User(String name, Integer id, Character sex) {
        this.name = name;
        this.id = id;
        this.sex = sex;
    }

    public User() {
    }
}
