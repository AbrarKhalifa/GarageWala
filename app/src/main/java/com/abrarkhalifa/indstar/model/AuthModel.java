package com.abrarkhalifa.indstar.model;

public class AuthModel {
    String  username,email,password,imageuri,userid;

    public AuthModel(String username, String email, String password, String imageuri, String userid) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.imageuri = imageuri;
        this.userid = userid;
    }

    public AuthModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
