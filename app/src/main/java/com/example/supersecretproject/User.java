package com.example.supersecretproject;

public class User {

    public String fullName;



    public String email;
    public String age;
    public String superSecretPassword;
    public String userStatus;


    public User() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User(String fullName, String age, String email, String superSecretPassword) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.superSecretPassword = superSecretPassword;

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserStatus() {
        return userStatus;
    }


    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

}