package com.huolicai.android.view.gestureverify;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午5:28
 * Email：yujunyao@yonglibao.com
 */

public interface IGestureVerifyView {

    void displayUserInfo(String account, String userHead);

    void verifySuccess();

    void verifyFail();

}
