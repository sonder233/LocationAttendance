package com.example.locationattendance.ui.main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.locationattendance.MainActivity;
import com.example.locationattendance.R;
import com.example.locationattendance.SearchGroupActivity;
import com.example.locationattendance.data.Group;
import com.example.locationattendance.data.GroupDao;
import com.example.locationattendance.data.UserConstants;
import com.example.locationattendance.speech.SpeechRvAdapter;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private int index;
    private static final String TAG = "SimpleCardFragment";

    View v_speech = null;
    View v_table = null;
    View v_more = null;

    public SwipeRefreshLayout mainRefresh;
    public List<Group> groupList=new ArrayList<>();
    SpeechRvAdapter adapter;

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
        View v=null;
        if (index ==0){
            v_speech = inflater.inflate(R.layout.tab_speech, null);
            v=v_speech;
            initSpeechPage(v_speech);
        }else if(index == 1){
            v_table = inflater.inflate(R.layout.tab_table, null);
            v=v_table;
        }else{
            v_more = inflater.inflate(R.layout.tab_more, null);
            v=v_more;
        }
        return v;
    }

    /**
     * 初始化聊天界面
     */
    public void initSpeechPage(View v){

        Button searchImage = v.findViewById(R.id.search_img);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.mContext, SearchGroupActivity.class));
            }
        });
        //下拉刷新
        mainRefresh = v.findViewById(R.id.main_refresh);
        mainRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        RecyclerView rv = v.findViewById(R.id.main_recyclerView);
        //初始化列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        adapter =new SpeechRvAdapter(getContext(),groupList);
        rv.setAdapter(adapter);
    }

    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //这里执行子线程操作
                    GroupDao dao = new GroupDao();
                    List<Group> group_list =  dao.getGroupsByUser(UserConstants.currentUser.getUser_id());

                    Message msg = Message.obtain();
                    msg.what=1;
                    msg.obj=group_list;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    groupList =(List<Group>)msg.obj;
                    if (groupList.size() != 0){
                        Log.d(TAG,"刷新成功");
                        for (Group g : groupList){
                            Log.d(TAG,g.getGroupName());
                        }
                        adapter.updateResultList(groupList);
                        adapter.notifyDataSetChanged();
                        LinearLayout layout = v_speech.findViewById(R.id.main_none_layout);
                        layout.setVisibility(View.INVISIBLE);
                        //停止刷新动画
                        mainRefresh.setRefreshing(false);
                    }
                    break;
            }
        }
    };
}