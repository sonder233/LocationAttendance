package com.example.locationattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.locationattendance.data.Group;
import com.example.locationattendance.data.GroupDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchGroupActivity extends AppCompatActivity {
    private static final String TAG = "SearchGroupActivity";

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.search_result_rv)
    RecyclerView searchRv;
    @BindView(R.id.no_result_layout)
    LinearLayout no_result_Layout;

    List<Group> result_list= new ArrayList<>();
    GroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_group);
        ButterKnife.bind(this);
        //初始化搜索栏
        initSearch();
        //初始化列表
        initRecyclerView();
    }
    public void initRecyclerView(){
        Group group = new Group("12345678"
        ,"高数","gaolong","2332447236",1,1);
        group.setImageId(R.drawable.icon_group_head);
        //result_list.add(group);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        searchRv.setLayoutManager(layoutManager);
        groupAdapter =new GroupAdapter(result_list);
        searchRv.setAdapter(groupAdapter);
    }

    Handler mHandler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    result_list =(List<Group>) msg.obj;
                    if (result_list.size() == 0){
                        no_result_Layout.setVisibility(View.VISIBLE);
                    }else{
                        no_result_Layout.setVisibility(View.INVISIBLE);
                        searchRv.setVisibility(View.VISIBLE);
                        //result_list.clear();
                        result_list =(List<Group>) msg.obj;
                        groupAdapter.updateResultList(result_list);
                        groupAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
    /**
     * 初始化搜索栏
     */
    public void initSearch(){
        searchView.setIconifiedByDefault(false);//搜索框是否自动收缩
        searchView.setSubmitButtonEnabled(true);//是否显示搜索按钮
        searchView.setQueryHint("签到群名称/群号");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GroupDao dao = new GroupDao();
                        List<Group> result_list = dao.queryGroup(s);
                        if (result_list == null){
                            Looper.prepare();
                            Toast.makeText(SearchGroupActivity.this, "无查询结果", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }else{
                            Message msg = Message.obtain();
                            msg.what=1;
                            msg.obj=result_list;
                            mHandler.sendMessage(msg);
                        }
                    }
                }).start();
                Log.d(TAG,Thread.currentThread().getName());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                no_result_Layout.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText( SearchGroupActivity.this, "Close", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}