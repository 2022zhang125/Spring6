package com.believesun.instantiation;


import java.util.Date;

public class Student {
    private Date birth;

    public Date getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "birth=" + birth +
                '}';
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
