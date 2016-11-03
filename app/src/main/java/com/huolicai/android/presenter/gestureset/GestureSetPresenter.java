package com.huolicai.android.presenter.gestureset;

import android.text.TextUtils;

import com.huolicai.android.model.gestureset.GestureSetModelImp;
import com.huolicai.android.model.gestureset.IGestureSetModel;
import com.huolicai.android.utils.Utils;
import com.huolicai.android.view.gestureset.IGestureSetView;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午1:42
 * Email：yujunyao@yonglibao.com
 */

public class GestureSetPresenter {

    private IGestureSetModel mGestureSetModel;
    private IGestureSetView mGestureSetView;

    public GestureSetPresenter(IGestureSetView view) {
        this.mGestureSetView = view;
        this.mGestureSetModel = new GestureSetModelImp();
    }

    /**设置密码*/
    public void setGesturePwd(String gesturePwd) {
        mGestureSetModel.setGesturePwd(gesturePwd);
    }

    /**验证密码的有效性*/
    public boolean isInputPwdValudate(String password) {
        if(TextUtils.isEmpty(password) || password.length() < 4) {
            return false;
        }
        return true;
    }

    /**比较第一次和第二次*/
    public boolean isFirstEqualTwice(String first, String twice) {
        return Utils.strCompare(first, twice);
    }

    /**更新指示图案*/
    public void updateIndicate(String password) {
        mGestureSetView.updateIndicate(password);
    }

    /**第一次输入正确*/
    public void firstInputRight() {
        mGestureSetView.showRightTips();
    }

    /**第二次输入与第一次不一样*/
    public void twiceInputWrong() {
        mGestureSetView.showWrongTips();
    }

    /**重新设置手势密码*/
    public void resetGesture() {
        mGestureSetView.resetGesture();
    }

}
