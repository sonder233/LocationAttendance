package com.example.locationattendance.data;

public class User {
    private int id;
    private String user_id;
    private String username;
    private String password;
    private String real_name;
    private String phone;

    public User() {
    }

    public User(int id, String user_id, String username, String password, String real_name, String phone) {
        this.id = id;
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.real_name = real_name;
        this.phone = phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
