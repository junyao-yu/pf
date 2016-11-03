package com.huolicai.android.presenter.register;

import com.huolicai.android.model.register.IRegisterModel;
import com.huolicai.android.model.register.IRegisterModelImp;
import com.huolicai.android.presenter.BasePresenter;
import com.huolicai.android.view.register.IRegisterView;

/**
 * Auth：yujunyao
 * Since: 2016/4/14 14:28
 * Email：yujunyao@yonglibao.com
 */
public class RegisterPresenter extends BasePresenter{

    private IRegisterModel mRegisterModel;
    private IRegisterView mRegisterView;

    public RegisterPresenter(IRegisterView view) {
        super(view);
        this.mRegisterView = view;
        mRegisterModel = new IRegisterModelImp();
    }

    public void accountFocusEvent(boolean bFlag) {
        mRegisterView.accountFocusEvent(bFlag);
    }

    public void vcodeFocusEvent(boolean bFlag) {
        mRegisterView.vcodeFocusEvent(bFlag);
    }

    public void buttonEnable() {
        mRegisterView.buttonEnable();
    }

    public void buttonDisable() {
        mRegisterView.buttonDisable();
    }

}
