package com.example.locationattendance.data;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    private static final String TAG = "GroupDao";

    Connection conn = null;

    public GroupDao() {
        this.conn = DbUtil.getConn(DbConstants.DATABASE_NAME);
    }

    public boolean addGroup(Group group) {
        boolean result =false;
        String sql="insert into mygroup value(?,?,?,?,?,?);";
        //String sql = "insert into group values('66471876','Computer','gaolong')";
        //预编译
        PreparedStatement ps = null; //预编译SQL，减少sql执行
        try {
            if (conn == null){
                Log.d(TAG,"conn为空");
            }else{
                ps = conn.prepareStatement(sql);
                ps.setString(1,group.getGroupId());
                ps.setString(2,group.getGroupName());
                ps.setString(3,group.getCreator());
                ps.setString(4,group.getCreator_id());
                ps.setInt(5,group.getDay_of_week());
                ps.setInt(6,group.getIndex_of_day());
                //执行
                ps.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key 根据关键词检索群组
     * @return
     */
    public List<Group> queryGroup(String key){
        List<Group> group_list = new ArrayList<>();

        String sql = "select * from mygroup where group_id = "+key;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                //Log.d(TAG,rs.getString("group_id")+"\t"+rs.getString("creator"));
                Group group=new Group();
                group.setGroupId(rs.getString("group_id"));
                group.setGroupName(rs.getString("group_name"));
                group.setCreator(rs.getString("creator"));
                group.setCreator_id(rs.getString("creator_id"));
                group.setDay_of_week(rs.getInt("day_of_week"));
                group.setIndex_of_day(rs.getInt("index_of_day"));
                Log.d(TAG,group.getGroupName());
                //添加至列表里
                group_list.add(group);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return group_list;
    }

    public Group getGroupByGroupId(String groupId){
        Group group = new Group();
        String sql = "select * from mygroup where group_id = "+groupId;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            //因为groupID是主键，所以只有一个结果
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){ //只有一个结果还循环，因为ResultSet是个结果集
                group.setGroupId(rs.getString("group_id"));
                group.setGroupName(rs.getString("group_name"));
                group.setCreator(rs.getString("creator"));
                group.setCreator_id(rs.getString("creator_id"));
                group.setDay_of_week(rs.getInt("day_of_week"));
                group.setIndex_of_day(rs.getInt("index_of_day"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return group;
    }

    public List<Group> getGroupsByUser(String userId) throws SQLException {
        List<Group> list = new ArrayList<>();
        String sql = "select * from group_members where user_id = "+userId;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                String group_id = rs.getString("group_id");
                Group group = getGroupByGroupId(group_id);
                list.add(group);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return list;
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
