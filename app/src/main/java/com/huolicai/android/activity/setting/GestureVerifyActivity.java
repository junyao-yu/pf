package com.huolicai.android.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureUtils;
import android.improving.utils.Init;
import android.improving.utils.tools.ImageDisplay;
import android.improving.utils.views.gesturelock.GestureContentView;
import android.improving.utils.views.gesturelock.GestureDrawline;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.common.Constants;
import com.huolicai.android.common.SpKey;
import com.huolicai.android.presenter.gestureverify.GestureVerifyPresenter;
import com.huolicai.android.utils.AnimationUtils;
import com.huolicai.android.utils.Utils;
import com.huolicai.android.view.gestureverify.IGestureVerifyView;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 16/4/8 上午10:31
 * Email：yujunyao@yonglibao.com
 */

public class GestureVerifyActivity extends BaseActivity implements IGestureVerifyView{

    @Bind(R.id.gesture_verify_user_head)
    ImageView gestureVerifyUserHead;
    @Bind(R.id.gesture_verify_user_mobile)
    TextView gestureVerifyUserMobile;
    @Bind(R.id.gesture_verify_wrong)
    TextView gestureVerifyWrong;
    @Bind(R.id.gesture_verify_container)
    FrameLayout gestureVerifyContainer;

    private String gesturePwd = "";
    private GestureContentView mGestureContentView = null;
    private int allowWrongTimes = 0;
    private GestureVerifyPresenter mGestureVerifyPresenter = null;
    private String account = "";
    private String userHead = "";

    public static void startActivity(Activity mActivity) {
        Intent intent = new Intent(mActivity, GestureVerifyActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gesture_verify);
        ButterKnife.bind(this);
    }

    @Override
    protected String currActivityName() {
        return getResources().getString(R.string.activity_gesture_verify);
    }

    @Override
    protected void initSubView() {
        gesturePwd = Init.spUtil.getValueString(SpKey.GESTURE_LOCK_PASSWORD, "");
        mGestureContentView = new GestureContentView(this);
        mGestureContentView.init(true, gesturePwd, new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {

            }

            @Override
            public void checkedSuccess() {
                mGestureVerifyPresenter.verifySuccess();
            }

            @Override
            public void checkedFail() {
                mGestureVerifyPresenter.verifyFail();
            }
        });
        mGestureContentView.setParentView(gestureVerifyContainer);
    }

    @Override
    protected void initSubData() {
        account = Init.spUtil.getValueString(SpKey.USER_ACCOUNT, "");
        userHead = Init.spUtil.getValueString(SpKey.USER_HEAD_URL, "");
        mGestureVerifyPresenter = new GestureVerifyPresenter(this);
        mGestureVerifyPresenter.setUserInfo(account, userHead);
        mGestureVerifyPresenter.displayUserInfo();
    }

    @OnClick(R.id.gesture_verify_forget)
    public void onClick(View view) {

    }

    @Override
    public void displayUserInfo(String account, String userHead) {
        ImageDisplay.imageViewDisplay(gestureVerifyUserHead, userHead);
        gestureVerifyUserMobile.setText(account);
    }

    @Override
    public void verifySuccess() {
        mGestureContentView.clearDrawlineState(0L);
        finish();
    }

    @Override
    public void verifyFail() {
        mGestureContentView.clearDrawlineState(1500L);
        allowWrongTimes++;
        int pos = Constants.GESTURE_INPUT_WRONG_TIMES - allowWrongTimes;
        if(pos == 0) {

        }else {
            gestureVerifyWrong.setText(Utils.id2Str(mContext,R.string.text_gesture_verify_wrong, pos));
            AnimationUtils.setGestureTipsShake(gestureVerifyWrong);
        }
    }
}
