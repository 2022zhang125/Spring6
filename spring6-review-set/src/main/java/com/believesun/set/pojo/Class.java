package com.believesun.set.pojo;

public class Class {
    private String name;

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class() {
    }

    public Class(String name) {
        this.name = name;
    }
}
