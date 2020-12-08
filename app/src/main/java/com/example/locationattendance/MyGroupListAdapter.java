package com.example.locationattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyGroupListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String[] groupStrings;
    private String[][] childStrings;

    public MyGroupListAdapter(Context context, String[] groupStrings, String[][] childStrings) {
        this.context = context;
        this.groupStrings = groupStrings;
        this.childStrings = childStrings;
    }

    @Override
    public int getGroupCount() {
        return groupStrings.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return childStrings[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return groupStrings[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return childStrings[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_group_list, viewGroup, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView) view.findViewById(R.id.label_expand_group);
            view.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }
        groupViewHolder.tvTitle.setText(groupStrings[i]);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_child_list, viewGroup, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView) view.findViewById(R.id.label_expand_child);
            view.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        childViewHolder.tvTitle.setText(childStrings[i][i1]);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    static class GroupViewHolder {
        TextView tvTitle;
    }
    static class ChildViewHolder {
        TextView tvTitle;
    }
}
