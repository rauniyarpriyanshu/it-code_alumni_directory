package com.pr.alumni_directory.db;

import java.io.Serializable;

public class LoginInfo implements Serializable {
    private String userName;

    public boolean isAdmin() {
        return admin;
    }

    public LoginInfo(){

    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    private boolean admin=false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    public LoginInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }







}
