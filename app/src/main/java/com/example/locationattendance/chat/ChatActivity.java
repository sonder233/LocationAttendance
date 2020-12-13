package com.example.locationattendance.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.locationattendance.R;
import com.example.locationattendance.data.Group;
import com.example.locationattendance.data.UserConstants;
import com.example.locationattendance.utils.DateUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    public Group currentGroup=null;
    public List<MsgBean> msgList = new ArrayList<>();
    public ChatRvAdapter chatRvAdapter;
    public Socket socket;


    @BindView(R.id.et_sendMsg)
    EditText sendMsg_et;
    @BindView(R.id.bt_sendMsg)
    Button sendMsgBt;
    @BindView(R.id.bt_sendOther)
    Button sendOtherBt;
    @BindView(R.id.rv_chatList)
    RecyclerView chat_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        //获取当前群组的对象
        Group group = new Group("12345678"
                ,"高数"
                ,"高龙"
                ,"2332447236"
                ,1,1);
//        Intent intent = getIntent();
//        String JsonData = intent.getStringExtra("currentGroup");
//        Group group = new Gson().fromJson(JsonData,Group.class);
//        Log.d(TAG,group.getGroupName());
        currentGroup = group;//设置当前群组
        //初始化文字输入栏
        initEditText();
        //初始化消息列表内容
        initMsgList();
        Log.d(TAG,msgList.size()+"");
//        for(MsgBean msgBean:msgList){
//            Log.d(TAG,msgBean.getName());
//        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chat_recyclerView.setLayoutManager(layoutManager);
        chatRvAdapter=new ChatRvAdapter(msgList);
        chat_recyclerView.setAdapter(chatRvAdapter);
        Log.d(TAG,chatRvAdapter.getItemCount()+"");

        //connectService();
        //log2Service();

    }

    private Handler mHandler =  new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    MsgBean msgBean = (MsgBean) msg.obj;
                    if(msgBean.getName().equals(UserConstants.currentUser.getUsername())){
                        //对消息的方向判断一下
                        msgBean.setMsgOrientation(2);
                    }else{
                        msgBean.setMsgOrientation(1);
                    }
                    msgList.add(msgBean);
                    chatRvAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };


    /**
     * 与服务器连接
     */
    public void connectService(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket= new Socket("10.12.27.232",10010);
                    InputStream inputStream = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1){
                        String data = new String(buffer,0,len);
                        MsgBean msgFromService = new Gson().fromJson(data,MsgBean.class);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = msgFromService;
                        mHandler.sendMessage(msg);

                        Log.d(TAG,"收到来自:"+msgFromService.getName()
                                        +"的消息:"+msgFromService.getMsg()
                                );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public void initMsgList(){
        MsgBean msgBean =  new MsgBean();
        msgBean.setMsg("我是憨憨我是憨憨我是憨憨我是憨憨我是憨憨我是憨憨我是憨憨我是憨憨");
        msgBean.setMsgType(1);//1代表文字消息
        msgBean.setMsgOrientation(1);
        msgBean.setName("高龙");
        Date dateTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm E");
        String time_str = sdf.format(dateTime);
        msgBean.setTime(time_str);
        msgList.add(msgBean);

        MsgBean msgBean2 =  new MsgBean();
        msgBean2.setMsg("对对对对对对对");
        msgBean2.setMsgType(1);//1代表文字消息
        msgBean2.setMsgOrientation(2);
        msgBean2.setName("托尼");
        Date dateTime2 = new Date();
        String time_str2 = sdf.format(dateTime2);
        msgBean.setTime(time_str2);
        msgList.add(msgBean2);

        MsgBean msgBean3 =  new MsgBean();
        msgBean3.setMsg("11");
        msgBean3.setMsgType(2);//1代表文字消息
        msgBean3.setMsgOrientation(3);
        msgBean3.setName("托尼");
        msgBean.setTime(time_str2);
        msgList.add(msgBean3);



    }

    public void initEditText(){
        sendMsg_et.addTextChangedListener(textWatch);//输入内容时右侧按钮变化
    }

    /**
     * 发送消息
     */
    @OnClick(R.id.bt_sendMsg)
    public void onClick(){
        //获取要发送的消息-文字类型
        String text = sendMsg_et.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //测试用
                    MsgBean msgBean =  new MsgBean();
                    msgBean.setMsg(sendMsg_et.getText().toString());
                    msgBean.setMsgType(1);//1代表文字消息
                    msgBean.setMsgOrientation(1);//1代表向左
                    msgBean.setName(UserConstants.currentUser.getUsername());
                    Date dateTime = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm E");
                    String time_str = sdf.format(dateTime);
                    msgBean.setTime(time_str);
                    Gson gson = new Gson();
                    String strMsgBean = gson.toJson(msgBean);
                    Log.d(TAG,strMsgBean);

                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write((strMsgBean).getBytes("utf-8"));
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public void log2Service(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                OutputStream outputStream = null;
                try {
                    socket= new Socket("192.168.0.108",10010);
                    outputStream = socket.getOutputStream();
                    outputStream.write(("id"+UserConstants.currentUser.getUser_id()).getBytes("utf-8"));
                    outputStream.flush();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1){
                        String data = new String(buffer,0,len);
                        MsgBean msgFromService = new Gson().fromJson(data,MsgBean.class);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = msgFromService;
                        mHandler.sendMessage(msg);

                        Log.d(TAG,"收到来自:"+msgFromService.getName()
                                +"的消息:"+msgFromService.getMsg()
                        );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private TextWatcher textWatch=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
        }
        @Override
        public void afterTextChanged(Editable s) {

            //s:变化后的所有字符
            if(s.toString().equals("")){
                sendOtherBt.setVisibility(View.VISIBLE);
                sendMsgBt.setVisibility(View.GONE);
            }else{
                sendOtherBt.setVisibility(View.GONE);
                sendMsgBt.setVisibility(View.VISIBLE);
            }
        }
    };
}