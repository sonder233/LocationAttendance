package com.example.locationattendance.data;

public class Group {
    private String groupId;
    private String creator;
    private String groupName;

    public Group() {
    }

    public Group(String groupId, String creator, String groupName) {
        this.groupId = groupId;
        this.creator = creator;
        this.groupName = groupName;
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
