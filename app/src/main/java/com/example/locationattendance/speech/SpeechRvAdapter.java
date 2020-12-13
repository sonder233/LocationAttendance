package com.example.locationattendance.speech;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.locationattendance.R;
import com.example.locationattendance.chat.ChatActivity;
import com.example.locationattendance.data.Group;
import com.google.gson.Gson;

import java.util.List;

public class SpeechRvAdapter extends RecyclerView.Adapter<SpeechRvAdapter.ViewHolder>  {
    private List<Group> mGroupList;
    Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView groupImage;
        TextView groupNameTv;
        TextView groupLastMsgTv;
        ImageView isReadDot;

        public ViewHolder (View view)
        {
            super(view);
            groupImage = (ImageView) view.findViewById(R.id.main_group_icon);
            groupNameTv = (TextView) view.findViewById(R.id.main_group_name);
            groupLastMsgTv = (TextView) view.findViewById(R.id.main_group_lastMsg);
            isReadDot = (ImageView) view.findViewById(R.id.main_group_isRead);
        }

    }

    public  SpeechRvAdapter (Context context,List <Group> groupList){
        mContext = context;
        mGroupList = groupList;
    }

    @Override
    public SpeechRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speech_item,parent,false);
        SpeechRvAdapter.ViewHolder holder = new SpeechRvAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Group group  = mGroupList.get(position);
                Toast.makeText(view.getContext(), "进入"+group.getGroupName()+"群", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,ChatActivity.class);
                intent.putExtra("currentGroup",new Gson().toJson(group));
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SpeechRvAdapter.ViewHolder holder, int position){
        Group group = mGroupList.get(position);
        //有时间了再加上群头像设置，现在先弄一样的
        //holder.groupImage.setImageResource(group.getImageId());
        holder.groupImage.setImageResource(R.drawable.icon_group_head);
        holder.groupNameTv.setText(group.getGroupName());
        holder.groupLastMsgTv.setText(group.getCreator()+":没签到的同学赶快签到");
    }

    public void updateResultList(List<Group> list) {
        mGroupList = list;
    }


    @Override
    public int getItemCount(){
        return mGroupList.size();
    }
}
