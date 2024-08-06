package com.believesun.bean2;

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

    public Wife(String name, Husband husband) {
        this.name = name;
        this.husband = husband;
    }

    public String getName() {
        return name;
    }

    public Wife() {
    }

    public Wife(String name) {
        this.name = name;
    }
}
