package com.believesun.bean;

public class Wife {
    private String name;
    private Husband husband;

    @Override
    public String toString() {
        return "Wife{" +
                "name='" + name + '\'' +
                ", husband=" + husband.getName() +
                '}';
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public Wife(String name, Husband husband) {
        this.name = name;
        this.husband = husband;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wife() {
    }

    public Wife(String name) {
        this.name = name;
    }
}
