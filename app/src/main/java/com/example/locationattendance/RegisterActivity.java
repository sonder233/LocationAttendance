package com.example.locationattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class RegisterActivity extends AppCompatActivity {

    //用户隐私授权的结果,暂时先不管他
    public static boolean granted = false;

    @BindView(R.id.Bt_registerByAnotherPho)
    TextView RegisterByAnother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        MobSDK.submitPolicyGrantResult(granted,null);

        //绑定butterKnife
        ButterKnife.bind(this);
    }

    /*点击跳转手机号注册界面*/
    @OnClick(R.id.Bt_registerByAnotherPho)
    public void onclickRegisterAnother(View view){
        //startActivity(new Intent(RegisterActivity.this,VertifyActivity.class));

        sendCode(RegisterActivity.this);

        //调试用，直接验证通过
//        Intent intent = new Intent(RegisterActivity.this,PasswordActivity.class);
//        intent.putExtra("phone_number","17337803324");
//        startActivity(intent);
    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    // 国家代码，如“86”
                    UserUtils.Id_country = (String) phoneMap.get("country");
                    // 手机号码，如“13800138000”
                    UserUtils.PhoneNumber = (String) phoneMap.get("phone");

                    // TODO 利用国家代码和手机号码进行后续的操作
                    //Toast.makeText(context, "验证成功,手机号为："+UserUtils.PhoneNumber, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this,PasswordActivity.class);
                    intent.putExtra("phone_number",UserUtils.PhoneNumber);
                    startActivity(intent);

                } else{
                    // TODO 处理错误的结果
                    Toast.makeText(RegisterActivity.this, "验证码错误！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        page.show(context);
    }

}