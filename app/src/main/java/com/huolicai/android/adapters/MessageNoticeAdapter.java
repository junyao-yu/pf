package com.huolicai.android.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huolicai.android.base.BaseFragment;

import java.util.List;

/**
 * Created by gaofei on 2016/4/8.
 */

public class MessageNoticeAdapter extends FragmentPagerAdapter {
    List<BaseFragment> list;
    public MessageNoticeAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BaseFragment getItem(int arg0) {
        return list.get(arg0);
    }




}
