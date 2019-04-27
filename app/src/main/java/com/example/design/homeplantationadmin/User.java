package com.example.design.homeplantationadmin;

public class User {
    String user_name;
    String user_email;
    String user_number;
    String userimage_url;

    public User(String user_name, String user_email, String user_number, String userimage_url) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_number = user_number;
        this.userimage_url = userimage_url;
    }

    public User() {
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getUserimage_url() {
        return userimage_url;
    }

    public void setUserimage_url(String userimage_url) {
        this.userimage_url = userimage_url;
    }
}
