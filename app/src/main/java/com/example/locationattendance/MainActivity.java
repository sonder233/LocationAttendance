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

public class MainActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        InitBar();//通知栏变透明

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


    @OnClick({R.id.bt_confirm, R.id.tv_signIn, R.id.tv_forgotPwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                String userName = etUserName.getText().toString();
                String password = etUserPassword.getText().toString();
                if (!IsFormatCorrect(userName,password)){
                    //首先检查用户名密码格式是否正确，是否含有非法字符等等
                    Toast.makeText(this, "用户名或密码格式错误", Toast.LENGTH_SHORT).show();
                }else{
                    if (IsRightUserNameAndPwd(userName,password)){
                        //调用数据库，检查用户名和密码
                        startActivity(new Intent(MainActivity.this,TabActivity.class));
                    }
                }



                break;
            case R.id.tv_signIn:
                break;
            case R.id.tv_forgotPwd:
                break;
        }
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