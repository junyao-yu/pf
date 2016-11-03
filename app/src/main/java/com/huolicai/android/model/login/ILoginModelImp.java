package com.huolicai.android.model.login;

import com.huolicai.android.model.BaseModelImp;

/**
 * Auth：yujunyao
 * Since: 2016/3/30 11:26
 * Email：yujunyao@yonglibao.com
 */
public class ILoginModelImp extends BaseModelImp implements ILoginModel {

    private String account = "";
    private String password = "";

    @Override
    public void setInput(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
