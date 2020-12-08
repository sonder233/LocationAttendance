package com.example.locationattendance.data;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupDao {
    private static final String TAG = "GroupDao";

    public void addGroup(Group group) {
        Connection conn = DbUtil.getConn(DbConstants.DATABASE_NAME);
        String sql="insert into mygroup value(?,?,?);";
        //String sql = "insert into group values('66471876','Computer','gaolong')";
        //预编译
        PreparedStatement ps = null; //预编译SQL，减少sql执行
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,group.getGroupId());
            ps.setString(2,group.getGroupName());
            ps.setString(3,group.getCreator());
            //执行
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getAllGroup(){
        Connection conn = DbUtil.getConn(DbConstants.DATABASE_NAME);
        String sql = "select * from mygroup";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            Log.d(TAG,"群组列表");
            while (rs.next()){
                Log.d(TAG,rs.getString("group_id")+"\t"+rs.getString("creator"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
