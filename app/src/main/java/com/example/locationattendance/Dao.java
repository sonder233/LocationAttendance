package com.example.locationattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.locationattendance.data.DbConstants;

/**
 * 用于数据库的增删改查
 */
public class Dao {

    private static final String TAG = "Dao";
    private final MyDataBaseHelper helper;

    public Dao(Context context){
        helper = new MyDataBaseHelper(context);
    }

    public void insert(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",1);
        values.put("username","17337803324");
        values.put("password","123456");
        db.insert(DbConstants.TABLE_NAME,null,values);
        db.close();
    }
    public void delete(){

    }
    public void update(){

    }
    public void query(){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DbConstants.TABLE_NAME,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            Log.d(TAG,"查询结果username:"+username+"  password:"+password);
        }
        db.close();
    }

    public boolean queryUsername(String username){
        boolean result = true;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DbConstants.TABLE_NAME,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            String u = cursor.getString(1);
            if (u.equals(username) ){
                //对比结果
                result = false;
                Log.d(TAG,"用户名已存在");
            }
        }
        return  result;
    }

    public boolean queryPwd(String username,String password){
        boolean result = false;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DbConstants.TABLE_NAME,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            String u = cursor.getString(1);
            String p = cursor.getString(2);
            if (u.equals(username) && p.equals(password)){
                //对比结果
                result = true;
            }
            Log.d(TAG,"查询结果username:"+username+"  password:"+password);
        }
        db.close();
        return result;
    }
}
