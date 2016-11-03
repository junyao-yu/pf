package com.huolicai.android.model.resetpwd;

import com.huolicai.android.model.BaseModelImp;

/**
 * @author: guoyazhou
 * @date: 2016-04-13 18:29
 */
public class IResetPwdModelImp extends BaseModelImp implements IResetPwdModel {
    private static final String TAG = "IResetPwdModelImp";

    private String password = "";
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
