package com.huolicai.android.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.presenter.resetpwd.ResetPwdPresenter;
import com.huolicai.android.utils.Utils;
import com.huolicai.android.view.resetpwd.IResetPwdView;
import com.huolicai.android.widget.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: guoyazhou
 * @date: 2016-04-12 16:05
 */
public class ResetPwdActivity extends BaseActivity implements IResetPwdView {
    private static final String TAG = "ResetPwdActivity";
    @Bind(R.id.edt_reset_pwd)
    EditText edtResetPwd;
    @Bind(R.id.reset_text_input)
    TextInputLayout resetTextInput;

    private ResetPwdPresenter resetPwdPresenter;

    public static void startActivity(Activity mActivity) {
        Intent intent = new Intent(mActivity, ResetPwdActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);
    }

    @Override
    protected String currActivityName() {
        return Utils.id2Str(this, R.string.activity_reset_pwd);
    }

    @Override
    protected void initSubView() {
        resetPwdPresenter = new ResetPwdPresenter(this);

        edtResetPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //检查实际是否匹配
                resetPwdPresenter.checkPwd(s.toString());
            }
        });
    }

    @Override
    protected void initSubData() {

    }

    @OnClick(R.id.reset_confirm_btn)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_confirm_btn:
                    resetPwdPresenter.resetPwd(edtResetPwd.getText().toString().trim());
                break;
        }
    }

    @Override
    public void inputError(int errorMsg) {
        resetTextInput.setErrorEnabled(true);
        resetTextInput.setError(Utils.id2Str(this, errorMsg));
    }

    @Override
    public void inputRight() {
        resetTextInput.setErrorEnabled(false);
    }

    @Override
    public void resetSuccess() {
        ToastUtil.showToast(this, R.string.toast_reset_pwd_success);
        finish();
    }

    @Override
    public void resetFail(String failMsg) {
        ToastUtil.showToast(this, failMsg);

    }
}
