package com.anma.json;

import com.google.gson.annotations.SerializedName;

public class Cat {

    @SerializedName("name")
    private String name;
    private int age;

    public Cat () {}

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
}
