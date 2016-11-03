package com.huolicai.android.presenter.gestureverify;

import com.huolicai.android.model.gestureverify.GestureVerifyModelImp;
import com.huolicai.android.model.gestureverify.IGestureVerifyModel;
import com.huolicai.android.view.gestureverify.IGestureVerifyView;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午5:32
 * Email：yujunyao@yonglibao.com
 */

public class GestureVerifyPresenter {

    private IGestureVerifyModel mGestureVerifyModel;
    private IGestureVerifyView mGestureVerifyView;

    public GestureVerifyPresenter(IGestureVerifyView view) {
        this.mGestureVerifyView = view;
        this.mGestureVerifyModel = new GestureVerifyModelImp();
    }

    /**显示用户信息*/
    public void displayUserInfo() {
        mGestureVerifyView.displayUserInfo(mGestureVerifyModel.getAccount(), mGestureVerifyModel.getUserHead());
    }

    /**核实成功*/
    public void verifySuccess() {
        mGestureVerifyView.verifySuccess();
    }

    /**核实失败*/
    public void verifyFail() {
        mGestureVerifyView.verifyFail();
    }

    /**设置用户数据*/
    public void setUserInfo(String account, String userHead) {
        mGestureVerifyModel.setUserInfo(account, userHead);
    }

}
