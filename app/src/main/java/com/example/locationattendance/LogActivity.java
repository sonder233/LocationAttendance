package com.example.locationattendance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_userPassword)
    EditText etUserPassword;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.tv_signIn)
    TextView tvSignIn;
    @BindView(R.id.tv_forgotPwd)
    TextView tvForgotPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);
        InitBar();//通知栏变透明
        //调试用 默认用户名和密码
        etUserName.setText("17337803324");
        etUserPassword.setText("123456");
        //初始化Bmob
//        Bmob.initialize(this, "f9d5257c5eff55d2b05c3e55ce6640af");
//        Person person = new Person("17337803324","gao123456");
//        person.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if (e==null){
//                    Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }


    /**
     * 把通知栏变透明
     */
    public void InitBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    @OnClick({R.id.bt_confirm})
    public void onViewClicked(View view) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        //调试用，直接进主页面
        startActivity(new Intent(LogActivity.this, MainActivity.class));
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String userName = etUserName.getText().toString().trim();
//                        String password = etUserPassword.getText().toString().trim();
//                        if (!IsFormatCorrect(userName,password)){
//                            //首先检查用户名密码格式是否正确，是否含有非法字符等等
//                        }else{
//                            if (IsRightUserNameAndPwd(userName,password)){
//                                //调用数据库，检查用户名和密码
//                                UserDao userDao = new UserDao();
//                                User user = null; //这里的用户名是手机号
//                                try {
//                                    user = userDao.get(userName);
//                                    if (user.getPassword().equals(password)){
//                                        Log.d(TAG,"登录成功");
//                                        startActivity(new Intent(MainActivity.this,GroupActivity.class));
//                                    }else{
//                                        Looper.prepare();
//                                        Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_LONG).show();
//                                        Looper.loop();
//                                    }
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                    Looper.prepare();
//                                    Toast.makeText(getApplicationContext(),"程序出现问题啦~",Toast.LENGTH_LONG).show();
//                                    Looper.loop();
//                                }
//                            }
//                        }
//                    }
//                }).start();

    }

    public boolean IsFormatCorrect(String userName,String password){
        //暂时空着 直接true
        return true;
    }

    /**
     * @param userName 输入框获取的用户名
     * @param password 输入框获取的密码
     * @return  数据库中有返回true，否则返回false
     */
    public boolean IsRightUserNameAndPwd(String userName,String password){
        //暂时空着 直接true
        return true;
    }
}