package com.example.locationattendance.data;

import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtil {
    private static String TAG = "DbUtil";
    public static String IP = "cdb-qu1t87k0.cd.tencentcdb.com"; //这个是华为云服务器的ip地址
    public static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String port = "10200";
    private static String password = "gao789456";


    /**
     * @param dbName 数据库名称
     * @return 数据库连接
     */
    public static Connection getConn(String dbName){
        Connection connection = null;
        try{
            Class.forName(driver);
            String url = "jdbc:mysql://"+IP+":"+port+"/"+dbName;
            connection = DriverManager.getConnection(url,user,password);

            Log.d(TAG,"数据库连接：成功");
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG,"数据库连接：失败");
        }
        return connection;
    }



}
