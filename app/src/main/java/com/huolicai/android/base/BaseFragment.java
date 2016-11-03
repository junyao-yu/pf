package com.huolicai.android.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

/**
 * Auth：yujunyao
 * Since: 2016/3/14 16:20
 * Email：yujunyao@yonglibao.com
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**描述当前分页的title--便于友盟统计*/
    protected abstract String currPageName();

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(currPageName());
        MobclickAgent.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(currPageName());
        MobclickAgent.onPause(getActivity());
    }
}
