package com.example.locationattendance;

import com.example.locationattendance.data.Group;
import com.example.locationattendance.data.GroupDao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Group group = new Group("63437552","Computer","gaolong");
        GroupDao dao = new GroupDao();
        dao.addGroup(group);
        assertEquals(4,2+2);
    }
}