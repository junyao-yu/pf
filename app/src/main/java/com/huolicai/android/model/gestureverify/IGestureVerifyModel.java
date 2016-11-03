package com.huolicai.android.model.gestureverify;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午5:25
 * Email：yujunyao@yonglibao.com
 */

public interface IGestureVerifyModel {

    void setUserInfo(String account, String userHead);

    String getAccount();

    String getUserHead();

}
