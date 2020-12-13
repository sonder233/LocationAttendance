package com.example.locationattendance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.locationattendance.data.Group;
import com.example.locationattendance.data.GroupDao;
import com.example.locationattendance.data.User;
import com.example.locationattendance.data.UserConstants;
import com.example.locationattendance.entity.TabEntity;
import com.example.locationattendance.ui.main.SimpleCardFragment;
import com.example.locationattendance.utils.ViewFindUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static Context mContext = null;

    private static final String TAG = "MainActivity";
    public String[] groupStrings = {"我创建的签到群", "我加入的签到群", "我管理的签到群"};
    public String[][] childStrings = {
            {"高等数学", "创新实践", "线性代数"},
            {"操作系统", "计算机组成原理"},
            {"数据结构", "算法设计", "大学生英语"}
    };
    private String[] mStrings = new String[]{"高等数学", "创新实践", "大学生英语", "数据结构"};

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles =  {"消息","课表","更多"};
    private int[] mIconUnselectIds = {
        R.mipmap.tab_speech_unselect,R.mipmap.tab_table_unselect,R.mipmap.tab_more_unselect
    };
    private int[] mIconSelectIds = {
            R.mipmap.tab_speech_select,R.mipmap.tab_table_select,R.mipmap.tab_more_select
    };
    private View mDecorView;
    private ViewPager viewPager;
    private CommonTabLayout mTabLayout;



    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=getApplicationContext();
        //绑定butterknife
        ButterKnife.bind(this);
        //初始化状态栏
        initToolbar();
        //初始化viewpager
        initTabLayout();
        User user = new User(1
                ,"2332447236"
                ,"钢铁侠"
                ,"abc123"
                ,"高龙"
                ,"17337803324");
        //这里先直接创建 然后存起来，一会用shareperfere
        UserConstants.setCurrentUser(user);

    }


    /**
     * 初始化tablayout翻页
     */
    public void initTabLayout(){
        for(int i=0;i<mTitles.length;i++){
            mFragments.add(SimpleCardFragment.getInstance(i));
        }
        for (int i=0;i<mTitles.length;i++){
            mTabEntities.add(new TabEntity(mTitles[i],mIconSelectIds[i],mIconUnselectIds[i]));
        }
        mDecorView = getWindow().getDecorView();
        viewPager = ViewFindUtils.find(mDecorView,R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout=ViewFindUtils.find(mDecorView,R.id.tab_layout);
        //先产生一个随机数
        Random mRandom = new Random();
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置起始页面index
        viewPager.setCurrentItem(0);
        //两位数
        mTabLayout.showMsg(0, 55);
        mTabLayout.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout.showMsg(1, 100);
        mTabLayout.setMsgMargin(1, -5, 5);
        //设置未读消息红点
        mTabLayout.showDot(2);
        MsgView rtv_2_2 = mTabLayout.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, dp2px(7.5f));
        }

        //设置未读消息背景
        mTabLayout.showMsg(3, 5);
        mTabLayout.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        return true;
    }


    /**
     * @param length 字符串长度
     * @return 返回随机的字符串
     */
    public static String getRandomString(int length){
        String str="123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(8);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 设置状态栏和toolbar
     */
    public void initToolbar(){
        Helper.setStatusBarColor(this,getResources().getColor(R.color.BlueToolbar));
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(getDrawable(R.drawable.add_icon));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.toolbar_menu_create:
                        //Toast.makeText(GroupActivity.this, "创建群聊", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,CreateGroupActivity.class));
                        //showCreateDialog();
                        break;
                    case R.id.toolbar_menu_add:
                        mTabLayout.showMsg(0, 44);
                        Toast.makeText(MainActivity.this, "添加群聊", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }



    /**
     * 把通知栏变透明
     */
    public void InitBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 页面适配器
     */
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    /**
     * @param dp dp值
     * @return 对应的px的值
     */
    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}