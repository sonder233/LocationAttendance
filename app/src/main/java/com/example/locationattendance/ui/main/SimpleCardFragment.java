package com.example.locationattendance.ui.main;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.locationattendance.R;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private int index;



    public static SimpleCardFragment getInstance(int index) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.index = index;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        if (index ==0){
            v = inflater.inflate(R.layout.tab_speech, null);
        }else if(index == 1){
            v = inflater.inflate(R.layout.tab_table, null);
        }else{
            v = inflater.inflate(R.layout.tab_more, null);
        }
        return v;
    }
}