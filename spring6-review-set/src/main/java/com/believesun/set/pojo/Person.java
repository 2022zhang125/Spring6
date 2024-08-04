package com.believesun.set.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Person {
    private String[] favoriteFoods;
    private List<String> hobbit;

    @Override
    public String toString() {
        return "Person{" +
                "favoriteFoods=" + Arrays.toString(favoriteFoods) +
                ", hobbit=" + hobbit +
                '}';
    }

    public List<String> getHobbit() {
        return hobbit;
    }

    public void setHobbit(List<String> hobbit) {
        this.hobbit = hobbit;
    }

    public Person(String[] favoriteFoods, List<String> hobbit) {
        this.favoriteFoods = favoriteFoods;
        this.hobbit = hobbit;
    }

    public String[] getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(String[] favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public Person(String[] favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public Person() {
    }
}
