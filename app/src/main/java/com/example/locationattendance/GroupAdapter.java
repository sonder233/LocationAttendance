package com.example.locationattendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.locationattendance.data.Group;

import org.w3c.dom.Text;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private List<Group> mGroupList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView groupImage;
        TextView groupNameTv;
        TextView groupIntro;
        Button groupJoinBt;

        public ViewHolder (View view)
        {
            super(view);
            groupImage = (ImageView) view.findViewById(R.id.group_head_icon);
            groupNameTv = (TextView) view.findViewById(R.id.search_group_name);
            groupIntro = (TextView) view.findViewById(R.id.search_group_intro);
            groupJoinBt = (Button) view.findViewById(R.id.search_join_bt);
        }

    }

    public  GroupAdapter (List <Group> groupList){
        mGroupList = groupList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.groupJoinBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Group group  = mGroupList.get(position);
                Toast.makeText(view.getContext(), "你想加入"+group.getGroupName()+"群吗？", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Group group  = mGroupList.get(position);
                Toast.makeText(view.getContext(), "你想加入"+group.getGroupName()+"群吗？", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Group group = mGroupList.get(position);
        //有时间了再加上群头像设置，现在先弄一样的
        //holder.groupImage.setImageResource(group.getImageId());
        holder.groupImage.setImageResource(R.drawable.icon_group_head);
        holder.groupNameTv.setText(group.getGroupName());
        holder.groupIntro.setText("群主："+group.getCreator());
    }

    public void updateResultList(List<Group> list) {
        mGroupList = list;
    }


    @Override
    public int getItemCount(){
        return mGroupList.size();
    }
}
