package com.example.locationattendance.chat;

public class MsgBean {
    private String msg;
    private String name;
    private String time;
    private int msgType; // 1-文字消息；2-签到消息
    private int msgOrientation;//1-在屏幕左边；2-在屏幕右边 3-签到左边 4-签到右边

    public MsgBean() {
    }

    public int getMsgOrientation() {
        return msgOrientation;
    }

    public void setMsgOrientation(int msgOrientation) {
        this.msgOrientation = msgOrientation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
