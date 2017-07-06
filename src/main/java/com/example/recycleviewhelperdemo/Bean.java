package com.example.recycleviewhelperdemo;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class Bean {
    private String name;
    private String adress;
    private int age;

    public Bean(String name, String adress, int age) {
        this.name = name;
        this.adress = adress;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
