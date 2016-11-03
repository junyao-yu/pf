package com.huolicai.android.base;

import android.improving.utils.InitApplication;

import com.huolicai.android.utils.UmengInitUtil;

/**
 * Auth：yujunyao
 * Since: 2016/3/8 17:04
 * Email：yujunyao@yonglibao.com
 */
public class BaseApplication extends InitApplication {
    @Override
    public void onCreated() {
        super.onCreated();

        //初始化友盟推送
        UmengInitUtil.umengMessageInit(this);
    }
}
