package com.example.locationattendance.chat;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationattendance.R;
import com.example.locationattendance.sign.MapActivity;
import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

public class ChatRvAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ChatRvAdapter";
    private List<MsgBean> mMsgList;
    private static final int LEFT_TYPE = 1;
    private static final int RIGHT_TYPE =2;
    private static final int SIGN_LEFT_TYPE =3;
    private static final int SIGN_RIGHT_TYPE =4;

    public  ChatRvAdapter (List <MsgBean> List){
        mMsgList = List;
    }

    /**
     * 左边文字消息样式
     */
    static class LeftViewHolder extends RecyclerView.ViewHolder{
        ImageView userHeadImg;
        BubbleTextView bubbleTextView;
        TextView msgContext;
        TextView msgTime;
        Button msgSender;//发送者

        public LeftViewHolder (View view)
        {
            super(view);
            userHeadImg = (ImageView) view.findViewById(R.id.chat_item_head_left);
            bubbleTextView = (BubbleTextView) view.findViewById(R.id.bubbleView_text_left);
        }
    }

    /**
     * 右边文字消息样式
     */
    static class RightViewHolder extends RecyclerView.ViewHolder{
        ImageView userHeadImg;
        BubbleTextView bubbleTextView;
        TextView msgContext;
        TextView msgTime;
        Button msgSender;//发送者

        public RightViewHolder (View view)
        {
            super(view);
            userHeadImg = (ImageView) view.findViewById(R.id.chat_item_head_right);
            bubbleTextView = (BubbleTextView) view.findViewById(R.id.bubbleView_text_right);
        }

    }

    /**
     * 左边签到样式
     */
    static class SignLiftViewHolder extends RecyclerView.ViewHolder{
        ImageView mapImageView;
        public SignLiftViewHolder(View view){
            super(view);
            mapImageView = (ImageView) view.findViewById(R.id.map_iv_left);
        }
    }

    /**
     * 右边签到样式
     */
    static class SignRightViewHolder extends RecyclerView.ViewHolder{
        ImageView mapImageView;
        public SignRightViewHolder(View view){
            super(view);
            mapImageView = (ImageView) view.findViewById(R.id.map_iv_left);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RecyclerView.ViewHolder holder = null;
        Log.d(TAG,"viewType:"+viewType);
        switch (viewType){
            case LEFT_TYPE:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_item_left,parent,false);
                holder = new LeftViewHolder(view1);
                break;
            case RIGHT_TYPE:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_item_right,parent,false);
                holder = new RightViewHolder(view2);
                break;
            case SIGN_LEFT_TYPE:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_item_left,parent,false);
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.getContext().startActivity(new Intent(view.getContext(), MapActivity.class));
                    }
                });
                holder = new SignLiftViewHolder(view3);
                break;
            case SIGN_RIGHT_TYPE:
                View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_item_right,parent,false);
                holder = new SignRightViewHolder(view4);
                break;
        }
        if (holder == null){
            Log.d(TAG,"holder为空");
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        MsgBean msgBean = mMsgList.get(position);
        int item_ori=msgBean.getMsgOrientation();
        switch (item_ori){
            case LEFT_TYPE:
                LeftViewHolder leftViewHolder = (LeftViewHolder)holder;
                //holder.groupImage.setImageResource(group.getImageId());
                leftViewHolder.userHeadImg.setImageResource(R.drawable.user_head_icon_1);
                leftViewHolder.bubbleTextView.setText(msgBean.getMsg());
                break;
            case RIGHT_TYPE:
                RightViewHolder rightViewHolder = (RightViewHolder) holder;
                rightViewHolder.userHeadImg.setImageResource(R.drawable.user_head_icon_2);
                rightViewHolder.bubbleTextView.setText(msgBean.getMsg());
                break;
            case SIGN_LEFT_TYPE:
                SignLiftViewHolder signLiftHolder = (SignLiftViewHolder) holder;
                signLiftHolder.mapImageView.setImageResource(R.drawable.map);
                break;
            case SIGN_RIGHT_TYPE:
                SignRightViewHolder signRightHolder = (SignRightViewHolder) holder;
                signRightHolder.mapImageView.setImageResource(R.drawable.map);
                break;

        }

    }

    public void updateList(List<MsgBean> list) {
        mMsgList = list;
    }


    @Override
    public int getItemCount(){
        return mMsgList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG,mMsgList.get(position).getMsg()+":"+mMsgList.get(position).getMsgOrientation());
        return mMsgList.get(position).getMsgOrientation();
    }
}
