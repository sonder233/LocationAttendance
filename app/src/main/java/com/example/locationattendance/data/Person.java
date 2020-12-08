package com.example.locationattendance.data;

import cn.bmob.v3.BmobObject;

public class Person extends BmobObject {
    private String username;
    private String password;

    public Person(){}

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
