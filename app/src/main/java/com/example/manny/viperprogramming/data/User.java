package com.example.manny.viperprogramming.data;

/**
 * Created by manny on 6/9/16.
 */
public class User {

    private String email;
    private String userName;
    private String password;

    public User(){}

    public User(String email, String userName){
        this.email = email;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
