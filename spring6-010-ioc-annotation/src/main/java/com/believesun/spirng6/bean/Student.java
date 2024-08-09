package com.believesun.spirng6.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用@Value注解给简单类型进行赋值操作
 */
@Component
public class Student {
    private String name;
    private int age;
    private String id;
    public Student(@Value("王五") String name, @Value("98") int age, @Value("9282933") String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
