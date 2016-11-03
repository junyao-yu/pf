package com.huolicai.android.activity.messagenotice;

import android.content.Intent;
import android.graphics.Color;
import android.improving.utils.views.AppBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huolicai.android.R;
import com.huolicai.android.adapters.MessageNoticeAdapter;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.base.BaseFragment;
import com.huolicai.android.fragments.MessageParentFragment;
import com.huolicai.android.fragments.NoticeParentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 2016/4/8.
 */
public class MessageActivity extends BaseActivity {


    public final static int TYPE_NOTICE =0;
    public final static int TYPE_MESSAGE = 1;

    @Bind(R.id.message_notice_pager)
    ViewPager messageNoticePager;
    private List<BaseFragment> mListFragment;
    private MessageNoticeAdapter messageNoticeAdapter;

    private  LinearLayout parent;
    private TextView notice;
    private TextView message;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_message_notice);
        ButterKnife.bind(this);
    }

    @Override
    protected String currActivityName() {
        return getString(R.string.activity_message_notice);
    }

    @Override
    protected void initSubView() {

    }

    @Override
    protected void initSubData() {
        mListFragment = new ArrayList<>();
        mListFragment.add(NoticeParentFragment.newInstance());
        mListFragment.add(MessageParentFragment.newInstance());
        messageNoticeAdapter = new MessageNoticeAdapter(getSupportFragmentManager(), mListFragment);
        messageNoticePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchChoiceItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        messageNoticePager.setAdapter(messageNoticeAdapter);
    }

    @Override
    public void controlAppBar(AppBar appbar) {
        View view = LayoutInflater.from(this).inflate(R.layout.message_notice_bar , null);
        parent = (LinearLayout)view.findViewById(R.id.message_notice_parent);
        notice = (TextView)view.findViewById(R.id.notice_title);
        message = (TextView)view.findViewById(R.id.message_title);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchChoiceItem(TYPE_NOTICE);
                messageNoticePager.setCurrentItem(TYPE_NOTICE);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchChoiceItem(TYPE_MESSAGE);
                messageNoticePager.setCurrentItem(TYPE_MESSAGE);
            }
        });

        appbar.setMiddleCustom(view);
        super.controlAppBar(appbar);
    }

    private void switchChoiceItem(int itemPosition){
        switch (itemPosition){
            case TYPE_NOTICE:
                message.setTextColor(getResources().getColor(R.color.color_f63c54));
                notice.setTextColor(Color.WHITE);
                notice.setBackgroundResource(R.drawable.message_notice_select_bg);
                message.setBackgroundColor(Color.TRANSPARENT);
                break;
            case TYPE_MESSAGE:
                notice.setTextColor(getResources().getColor(R.color.color_f63c54));
                message.setTextColor(Color.WHITE);
                message.setBackgroundResource(R.drawable.message_message_select_bg);
                notice.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
    }
}
