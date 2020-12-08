package com.example.locationattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PasswordActivity extends AppCompatActivity {

    @BindView(R.id.ivBack)
    ImageView IV_back;
    @BindView(R.id.etPhone)
    EditText ET_phone;
    @BindView(R.id.etPassword)
    EditText ET_password;

    private String phoneNumber;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        //绑定butterKnife
        ButterKnife.bind(this);

        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phone_number");
        ET_phone.setText(phoneNumber);
        ET_phone.setEnabled(false);
        //调试用
        ET_password.setText("123456");
    }

    @OnClick(R.id.tvConfirm)
    public void OnClickRegister(){
        if (ET_password.getText().equals("")){
            Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT).show();
        }else{
            Dao dao = new Dao(PasswordActivity.this);
            password = ET_password.getText().toString().trim();
            //dao.insert();
            if(dao.queryUsername(phoneNumber)){
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*返回*/
    @OnClick(R.id.ivBack)
    public void OnClickBack(){
        finish();
    }
}