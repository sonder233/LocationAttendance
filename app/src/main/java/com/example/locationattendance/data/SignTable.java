package com.example.locationattendance.data;

public class SignTable {
    public String group_id;
    public String originator_id;
    public String time;
    public double longitude;
    public double latitude;
    public int sign_radius;
    public String receiver;
    public double rlongitude;
    public double rlatitude;
    public int state;
    public int done;
    public int result;

    public SignTable() {
    }

    public SignTable(String group_id, String originator_id, String time, double longitude, double latitude, int sign_radius, String receiver, double rlongitude, double rlatitude, int state, int done, int result) {
        this.group_id = group_id;
        this.originator_id = originator_id;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
        this.sign_radius = sign_radius;
        this.receiver = receiver;
        this.rlongitude = rlongitude;
        this.rlatitude = rlatitude;
        this.state = state;
        this.done = done;
        this.result = result;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getOriginator_id() {
        return originator_id;
    }

    public void setOriginator_id(String originator_id) {
        this.originator_id = originator_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getSign_radius() {
        return sign_radius;
    }

    public void setSign_radius(int sign_radius) {
        this.sign_radius = sign_radius;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getRlongitude() {
        return rlongitude;
    }

    public void setRlongitude(double rlongitude) {
        this.rlongitude = rlongitude;
    }

    public double getRlatitude() {
        return rlatitude;
    }

    public void setRlatitude(double rlatitude) {
        this.rlatitude = rlatitude;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
