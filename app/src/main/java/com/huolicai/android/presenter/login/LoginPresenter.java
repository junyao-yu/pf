package com.huolicai.android.presenter.login;

import com.huolicai.android.model.login.ILoginModel;
import com.huolicai.android.model.login.ILoginModelImp;
import com.huolicai.android.presenter.BasePresenter;
import com.huolicai.android.view.login.ILoginView;

/**
 * Auth：yujunyao
 * Since: 2016/3/30 11:20
 * Email：yujunyao@yonglibao.com
 */
public class LoginPresenter extends BasePresenter{

    private ILoginView mLoginView;

    private ILoginModel mLoginModel;

    public LoginPresenter(ILoginView view) {
        super(view);
        this.mLoginView = view;
        mLoginModel = new ILoginModelImp();
    }

    public void accountFocusEvent(boolean bFlag) {
        mLoginView.accountFocusEvent(bFlag);
    }

    public void passwordFocusEvent(boolean bFlag) {
        mLoginView.passwordFocusEvent(bFlag);
    }

    public void buttonEnable() {
        mLoginView.buttonEnable();
    }

    public void buttonDisable() {
        mLoginView.buttonDisable();
    }

}
