package com.huolicai.android.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.improving.utils.views.CommonEditView;
import android.improving.utils.tools.AndroidBugsSolution;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.presenter.login.LoginPresenter;
import com.huolicai.android.utils.AnimationUtils;
import com.huolicai.android.utils.Utils;
import com.huolicai.android.view.login.ILoginView;
import com.huolicai.android.widget.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/3/30 11:29
 * Email：yujunyao@yonglibao.com
 */
public class LoginActivity extends BaseActivity implements ILoginView{

    @Bind(R.id.login_top_logo)
    ImageView loginTopLogo;
    @Bind(R.id.login_input_account_logo)
    ImageView loginInputAccountLogo;
    @Bind(R.id.login_input_account)
    CommonEditView loginInputAccount;
    @Bind(R.id.login_input_password_logo)
    ImageView loginInputPasswordLogo;
    @Bind(R.id.login_input_password)
    CommonEditView loginInputPassword;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.login_input_ll)
    LinearLayout loginInputLl;

    private LoginPresenter loginPresenter = null;
    private Bitmap bitmap = null;
    private static final int ACCOUNT_INPUT_TYPE = 1;
    private static final int PASSWORD_INPUT_TYPE = 2;

    public static void startActivity(Activity mActivity) {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected String currActivityName() {
        return Utils.id2Str(this, R.string.activity_login);
    }

    @Override
    protected void initSubView() {
        getAppBar().setVisibility(View.GONE);
        loginInputAccount.setOnFocusCommonChangeListener(new OnFocusChangeListenerImp(ACCOUNT_INPUT_TYPE));
        loginInputPassword.setOnFocusCommonChangeListener(new OnFocusChangeListenerImp(PASSWORD_INPUT_TYPE));
        loginInputAccount.setOnTextChangeListener(new OnTextChangeListenerImp());
        loginInputPassword.setOnTextChangeListener(new OnTextChangeListenerImp());
    }

    @Override
    protected void initSubData() {
        loginPresenter = new LoginPresenter(this);
        loginPresenter.buttonDisable();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo);
        AndroidBugsSolution.assistActivity(this, new AndroidBugsSolution.OnKeyboardListener() {
            @Override
            public void onKeyboardChanged(int state) {
                switch (state) {
                    case AndroidBugsSolution.SHOW_KEYBOARD:
                        AnimationUtils.setLoginLogoScale(loginTopLogo, 1, 0);
                        AnimationUtils.setLoginInputUp(loginInputLl, 0, 0 - bitmap.getHeight());
                        break;
                    case AndroidBugsSolution.HIDE_KEYBOARD:
                        AnimationUtils.setLoginLogoScale(loginTopLogo, 0, 1);
                        AnimationUtils.setLoginInputUp(loginInputLl, 0 - bitmap.getHeight(), 0);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.login_forget_password, R.id.login_go_register, R.id.login_around, R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_forget_password:
                break;
            case R.id.login_go_register:
                break;
            case R.id.login_around:
                break;
            case R.id.login_btn:
                ToastUtil.showToast(mContext, "点击了登录");
                break;
        }
    }

    @Override
    public void accountFocusEvent(boolean bFlag) {
        if(bFlag) {
            loginInputAccountLogo.setImageResource(R.mipmap.icon_phone_sel);
        }else {
            loginInputAccountLogo.setImageResource(R.mipmap.icon_phone_unsel);
        }
    }

    @Override
    public void passwordFocusEvent(boolean bFlag) {
        if(bFlag) {
            loginInputPasswordLogo.setImageResource(R.mipmap.icon_password_sel);
        }else {
            loginInputPasswordLogo.setImageResource(R.mipmap.icon_password_unsel);
        }
    }

    @Override
    public void buttonEnable() {
        loginBtn.setTextColor(getResources().getColor(R.color.white));
        loginBtn.setEnabled(true);
    }

    @Override
    public void buttonDisable() {
        loginBtn.setTextColor(getResources().getColor(R.color.color_fd9aa7));
        loginBtn.setEnabled(false);
    }

    private class OnFocusChangeListenerImp implements CommonEditView.OnFocusCommonChangeListener {
        private int iType;
        private OnFocusChangeListenerImp(int iType) {
            this.iType = iType;
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            if(iType == ACCOUNT_INPUT_TYPE) {
                loginPresenter.accountFocusEvent(b);
            }else if(iType == PASSWORD_INPUT_TYPE) {
                loginPresenter.passwordFocusEvent(b);
            }
        }
    }

    private class OnTextChangeListenerImp implements CommonEditView.OnTextChangeListener {

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            if(!TextUtils.isEmpty(loginInputAccount.getText().toString()) && !TextUtils.isEmpty(loginInputPassword.getText().toString())) {
                loginPresenter.buttonEnable();
            }else {
                loginPresenter.buttonDisable();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.bitmapRecycle(bitmap);
    }

}
