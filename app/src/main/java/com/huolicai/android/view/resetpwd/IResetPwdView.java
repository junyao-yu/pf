package com.huolicai.android.view.resetpwd;

import com.huolicai.android.view.IBaseView;

/**
 * @author: guoyazhou
 * @date: 2016-04-14 10:58
 */
public interface IResetPwdView extends IBaseView {

    void inputError(int errorMsg);

    void inputRight();

    void resetSuccess();

    void resetFail(String failMsg);

}
