package com.huolicai.android.presenter.resetpwd;

import android.improving.utils.tools.ValidateUtil;

import com.huolicai.android.R;
import com.huolicai.android.model.resetpwd.IResetPwdModel;
import com.huolicai.android.model.resetpwd.IResetPwdModelImp;
import com.huolicai.android.presenter.BasePresenter;
import com.huolicai.android.view.resetpwd.IResetPwdView;

/**
 * @author: guoyazhou
 * @date: 2016-04-14 11:06
 */
public class ResetPwdPresenter extends BasePresenter {
    private static final String TAG = "ResetPwdPresenter";

    private IResetPwdModel resetPwdModel;
    private IResetPwdView resetPwdView;

    public ResetPwdPresenter(IResetPwdView view) {
        super(view);
        this.resetPwdView = view;
        resetPwdModel = new IResetPwdModelImp();
    }

    public boolean checkPwd(String str) {
        boolean isValidate = false;

        if (ValidateUtil.isChinese(str)) {
            resetPwdView.inputError(R.string.edit_input_pwd_error1);
        } else if (str.length() < 6) {
            resetPwdView.inputError(R.string.edit_input_pwd_error);
        } else if (str.length() > 15) {
            resetPwdView.inputError(R.string.edit_input_pwd_error2);
        } else {
            isValidate = true;
            resetPwdView.inputRight();
        }

        return isValidate;
    }

    public void resetPwd(String pwd) {

        if (checkPwd(pwd)) {
            //TODO重置密码
            resetPwdView.resetSuccess();
//            resetPwdView.resetFail();
        }


    }

}
