package com.example.supersecretproject;

public class Validate_User {




    public static Boolean validateFirstName(String firstName) {
        if (firstName.isEmpty()) {
            return false;
        } else if (firstName.length() < 2) {
            return false;
        } else if (firstName.contains("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
            return false;
        } else {
            return true;
        }


    }

    public static Boolean validateLastName(String firstName) {
        if (firstName.isEmpty()) {
            return false;
        } else if (firstName.length() < 2) {
            return false;
        } else if (firstName.contains("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
            return false;
        } else {
            return true;
        }

    }


    public static Boolean validatePassword(String password) {
        if (password.isEmpty()) {
            return false;
        } else if (password.length() <= 6) {
            return false;
        } else if (password.contains("(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validateEmail(String email) {
        if (email.isEmpty()) {
            return false;
        } else if (
                email.matches("[A-Za-z].*?@gmail\\.com")) {
            return false;
        } else
            return true;
    }

    public static Boolean validatePasswordConfirmation(String passwordConfirm, String password) {
        if (passwordConfirm.isEmpty()) {
            return false;
        } else if (!passwordConfirm.equals(password)) {
            return false;
        } else if (passwordConfirm.length() <= 6) {
            return false;
        } else {
            return true;
    }

}


            




}
