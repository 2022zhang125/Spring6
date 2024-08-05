package com.believesun.set.pojo;

public class Vip {
    private String email;

    @Override
    public String toString() {
        return "Vip{" +
                "email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vip(String email) {
        this.email = email;
    }

    public Vip() {
    }
}
