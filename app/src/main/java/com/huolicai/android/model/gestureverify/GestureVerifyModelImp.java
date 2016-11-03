package com.huolicai.android.model.gestureverify;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午5:27
 * Email：yujunyao@yonglibao.com
 */

public class GestureVerifyModelImp implements IGestureVerifyModel {

    private String account ="";
    private String userHead = "";

    @Override
    public void setUserInfo(String account, String userHead) {
        this.account = account;
        this.userHead = userHead;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getUserHead() {
        return userHead;
    }

}
