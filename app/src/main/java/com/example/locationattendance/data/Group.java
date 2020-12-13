package com.example.locationattendance.data;

public class Group {
    private String groupId;
    private String groupName;
    private String creator;
    private String creator_id;
    private int day_of_week = -1;
    private int index_of_day = -1;
    //这里只在RecyclerView中用过，不涉及数据库
    private int ImageId;

    public Group() {
    }

    public Group(String groupId, String groupName, String creator, String creator_id, int day_of_week, int index_of_day) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.creator = creator;
        this.creator_id = creator_id;
        this.day_of_week = day_of_week;
        this.index_of_day = index_of_day;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public int getIndex_of_day() {
        return index_of_day;
    }

    public void setIndex_of_day(int index_of_day) {
        this.index_of_day = index_of_day;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }


    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getCreator() {
        return creator;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
