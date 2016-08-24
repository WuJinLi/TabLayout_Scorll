package com.example.my.tablayoutfragment.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.my.tablayoutfragment.fragment.DummFragment;

import java.util.List;

/**
 * 自定义工具类，实现动态创建fragment的优化
 */
public class FragmentHelper {
    //fragment第一次创建调用的方法
    public static void replaceFragment(FragmentManager manager, int tabIndex, List<Fragment>
            list, int container, int enter, int exit) {
        //初始化事务
        FragmentTransaction transaction = manager.beginTransaction();
        //设置fragment切换的动画
        if (enter != 0 && exit != 0) {
            transaction.setCustomAnimations(enter, exit);
        }
        //将指定的fragment设置在frameLayout
        transaction.replace(container, list.get(tabIndex));
        //执行事务
        transaction.commit();
    }

    //碎片已经创建，切换调用的方法
    public static void switchFragment(FragmentManager manager, int tabIndex, List<Fragment> list,
                                      int container, int enter, int exit) {
        //初始化事物对象
        FragmentTransaction transaction = manager.beginTransaction();
        //设置碎片之间切换的动画
        if (enter != 0 && exit != 0) {
            transaction.setCustomAnimations(enter, exit);
        }
        //在切换碎片之前将之前显示的碎片隐藏
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isVisible()) {
                transaction.hide(list.get(i));
            }
        }
        //获取要显示的碎片
        Fragment fragment = list.get(tabIndex);
        //根据碎片的创建状态，调用不同的显示方法
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.replace(container, fragment);
        }
        //执行事务
        transaction.commit();
    }
}
