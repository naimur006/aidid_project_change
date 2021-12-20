package com.example.pojo;

public class User {
    public String username, name, email, phone,gender,birthday;

    public User(){

    }

    public User(String username,String name, String email, String phone,String gender,String birthday) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
    }
}
