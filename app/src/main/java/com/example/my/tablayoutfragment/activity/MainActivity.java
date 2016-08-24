package com.example.my.tablayoutfragment.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.my.tablayoutfragment.R;
import com.example.my.tablayoutfragment.fragment.DummFragment;
import com.example.my.tablayoutfragment.helper.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private String[] title;
    private List<Fragment> totalList = new ArrayList<>();
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //调用初始化UI控件的方法
        initView();
        //调用初始化数据的方法
        initData();

    }

    private void initData() {
        //获取字符串数组
        title = getResources().getStringArray(R.array.arrTabTitles);
        //动态的创建fragment放入集合中
        for (int i = 0; i < title.length; i++) {
            DummFragment dummFragment = DummFragment.newInstance(i, title[i]);
            totalList.add(dummFragment);
            tabLayout.addTab(tabLayout.newTab().setText(title[i]), i);
        }
        //初始化FragmentManger
        manager = getSupportFragmentManager();
        //默认情况下第一个tab被选中
        tabLayout.getTabAt(0).select();
        //对应第一个tab所对应的fragment
        FragmentHelper.replaceFragment(manager, 0, totalList, R.id.layout_container, 0, 0);
        //给tab设置滑动监听，实现tab的切换，伴随对应fragment的创建展示
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //展示指定的碎片
                FragmentHelper.switchFragment(manager, tab.getPosition(), totalList, R.id
                        .layout_container, 0, 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_main);
    }
}
