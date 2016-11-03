package com.huolicai.android.adapters;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huolicai.android.activity.messagenotice.MessageActivity;
import com.huolicai.android.base.BaseFragment;
import com.huolicai.android.common.Constants;
import com.huolicai.android.fragments.MessageFragment;
import com.huolicai.android.fragments.NoticeFragment;

/**
 * Created by gaofei on 2016/4/8.
 */

public class MNAdapter extends FragmentPagerAdapter {

    private int mType = 0;

    public MNAdapter(FragmentManager fm , int type) {
        super(fm);
        this.mType = type;
    }

    @Override
    public int getCount() {
        return Constants.MessageNotice.MESSAGE_NOTICE_CPOUNT;
    }

    @Override
    public BaseFragment getItem(int arg0) {
        if(mType == MessageActivity.TYPE_MESSAGE){
            return MessageFragment.newInstance(Color.WHITE);
        }else{
            return NoticeFragment.newInstance(Color.WHITE);
        }
    }




}
