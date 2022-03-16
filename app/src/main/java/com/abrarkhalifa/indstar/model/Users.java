package com.abrarkhalifa.indstar.model;

public class Users {

    String profilePic , userName , Email , Password , userId;

    public Users(String profilePic, String userName, String email, String password, String userId) {
        this.profilePic = profilePic;
        this.userName = userName;
        Email = email;
        Password = password;
        this.userId = userId;
    }

    public Users() {
    }



    public Users(String profilePic, String userName, String email, String password) {
        this.profilePic = profilePic;
        this.userName = userName;
        Email = email;
        Password = password;
    }

    public Users(String userName, String email, String password) {
        this.userName = userName;
        Email = email;
        Password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

