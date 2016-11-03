package com.huolicai.android.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.improving.utils.views.gesturelock.GestureContentView;
import android.improving.utils.views.gesturelock.GestureDrawline;
import android.improving.utils.views.gesturelock.LockIndicator;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.presenter.gestureset.GestureSetPresenter;
import com.huolicai.android.utils.AnimationUtils;
import com.huolicai.android.utils.Utils;
import com.huolicai.android.view.gestureset.IGestureSetView;
import com.huolicai.android.widget.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 16/4/8 上午10:23
 * Email：yujunyao@yonglibao.com
 */

public class GestureSetActivity extends BaseActivity implements IGestureSetView{
    @Bind(R.id.gesture_container)
    FrameLayout gestureContainer;
    @Bind(R.id.gesture_again_draw)
    TextView gestureAgainDraw;
    @Bind(R.id.gesture_tips)
    TextView gestureTips;
    @Bind(R.id.gesture_set_lock_indicator)
    LockIndicator gestureSetLockIndicator;

    private GestureContentView mGestureContentView = null;
    private GestureSetPresenter mGesturePresenter = null;
    private boolean isFirstLock = true;
    private String firstPwd = "";

    public static void startActivity(Activity mActivity) {
        Intent intent = new Intent(mActivity, GestureSetActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gesture_set);
        ButterKnife.bind(this);
    }

    @Override
    protected String currActivityName() {
        return getResources().getString(R.string.activity_gesture_set);
    }

    @Override
    protected void initSubView() {
        mGestureContentView = new GestureContentView(this);
        mGestureContentView.init(false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if(!mGesturePresenter.isInputPwdValudate(inputCode)) {
                    ToastUtil.showToast(mContext, R.string.toast_gesture_tips);
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }

                if(isFirstLock) {
                    firstPwd = inputCode;
                    mGesturePresenter.updateIndicate(inputCode);
                    mGestureContentView.clearDrawlineState(0L);
                    mGesturePresenter.firstInputRight();
                }else {
                    if(mGesturePresenter.isFirstEqualTwice(firstPwd, inputCode)) {
                        ToastUtil.showToast(mContext, R.string.toast_gesture_success);
                        mGestureContentView.clearDrawlineState(0L);
                        mGesturePresenter.setGesturePwd(inputCode);
                        finish();
                    }else {
                        mGesturePresenter.twiceInputWrong();
                    }
                }

            }

            @Override
            public void checkedSuccess() {

            }

            @Override
            public void checkedFail() {

            }
        });
        mGestureContentView.setParentView(gestureContainer);
    }

    @Override
    protected void initSubData() {
        mGesturePresenter = new GestureSetPresenter(this);
    }

    @OnClick({R.id.gesture_again_draw, R.id.gesture_skip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gesture_again_draw:
                mGesturePresenter.resetGesture();
                break;
            case R.id.gesture_skip:
                finish();
                break;
        }
    }

    @Override
    public void showWrongTips() {
        //TODO Animation
        gestureTips.setText(Html.fromHtml(Utils.id2Str(mContext, R.string.text_gesture_wrong_tips)));
        mGestureContentView.clearDrawlineState(1500L);
        AnimationUtils.setGestureTipsShake(gestureTips);
    }

    @Override
    public void showRightTips() {
        gestureTips.setText(Utils.id2Str(mContext, R.string.text_gesture_again));
        gestureAgainDraw.setVisibility(View.VISIBLE);
        isFirstLock = false;
    }

    @Override
    public void updateIndicate(String password) {
        gestureSetLockIndicator.setPath(password);
    }

    @Override
    public void resetGesture() {
        isFirstLock = true;
        mGesturePresenter.updateIndicate("");
        gestureTips.setText(Utils.id2Str(mContext, R.string.text_gesture_tips));
    }
}
