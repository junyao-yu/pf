package com.huolicai.android.model.register;

/**
 * Auth：yujunyao
 * Since: 2016/4/14 14:25
 * Email：yujunyao@yonglibao.com
 */
public interface IRegisterModel {

    void setInput(String account, String vcode);

    void setAccount(String account);

    void setVCode(String vcode);

}
