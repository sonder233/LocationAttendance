package com.example.locationattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.locationattendance.data.Group;
import com.example.locationattendance.data.GroupDao;

import java.sql.SQLException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateGroupActivity extends AppCompatActivity {

    @BindView(R.id.create_group_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        ButterKnife.bind(this);
        //初始化toolbar
        initToolbar();

        EditText et_group_id = findViewById(R.id.et_group_id);
        et_group_id.setText(getRandomString(8));//8位随机数字当作群号，先不检查是否重复，应该不会重复吧

        EditText et_group_name = findViewById(R.id.et_group_name);
        SpannableString s = new SpannableString("群聊名称(2-16个字)");
        AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(20,true);
        s.setSpan(textSize,0,s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et_group_name.setHint(s);
        //测试用
        et_group_name.setText("数据结构");

        Button Bt_Create_Group = findViewById(R.id.bt_create_group);
        Bt_Create_Group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_group_name.getText().toString().equals("")){
                    Toast.makeText(CreateGroupActivity.this, "名称不规范，请重新输入", Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("thread",Thread.currentThread().getName());
                            Group group = new Group(et_group_id.getText().toString(),
                                    et_group_name.getText().toString(),
                                    "高龙",
                                    "2332447236",
                                    1,3);
                            GroupDao dao = new GroupDao();
                            if(!dao.addGroup(group)){
                                Looper.prepare();
                                Toast.makeText(getBaseContext(), "网络错误，请检查网络", Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }else{
                                Looper.prepare();
                                Toast.makeText(getBaseContext(), "创建成功", Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
                        }
                    }).start();
                    CreateGroupActivity.this.finish();
                }
            }
        });
    }

    /**
     * 设置状态栏和toolbar
     */
    public void initToolbar(){
        Helper.setStatusBarColor(this,getResources().getColor(R.color.BlueToolbar));
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(getDrawable(R.drawable.add_icon));
        toolbar.setTitle("创建签到群");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * @param length 字符串长度
     * @return 返回随机的字符串
     */
    public static String getRandomString(int length){
        String str="123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(8);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}