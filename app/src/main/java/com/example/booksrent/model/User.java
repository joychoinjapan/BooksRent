package com.example.booksrent.model;

public class User {
    private String user_name;
    private int age;
    private String sex;

    public User(String user_name, int age, String sex) {
        this.user_name = user_name;
        this.age = age;
        this.sex = sex;
    }

    public User(){

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
