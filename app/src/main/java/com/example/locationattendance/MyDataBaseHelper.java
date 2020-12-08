package com.example.locationattendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.locationattendance.data.DbConstants;

public class MyDataBaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "MyDataBaseHelper";

    /**
     * @param context 上下文
     */
    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"创建数据库");
        String sql = "CREATE TABLE "+DbConstants.TABLE_NAME+" (_id integer, username varchar,password varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
