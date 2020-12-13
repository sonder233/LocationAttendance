package com.example.locationattendance.data;

public class UserConstants {
    public static User currentUser =  new User(2
            ,"2332447222"
            ,"雷神"
            ,"abc123"
            ,"张一"
            ,"13001360441");
    public static void setCurrentUser(User user){
        currentUser =user;
    }
}
