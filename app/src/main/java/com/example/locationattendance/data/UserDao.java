package com.example.locationattendance.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User get(String phone) throws SQLException {
        User user = null;
        Connection conn=DbUtil.getConn(DbConstants.DATABASE_NAME);

        String sql = "select * from  "+DbConstants.TABLE_NAME+" where phone=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,phone);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPhone(rs.getString("phone"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    }
}
