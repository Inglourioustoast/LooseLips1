package com.example.supersecretproject;

public class User {

    public String fullName;
    public String email;
    public String age;
    public String superSecretPassword;

    public User() {

    }

    public User(String fullName, String age, String email, String superSecretPassword) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.superSecretPassword = superSecretPassword;
    }



}