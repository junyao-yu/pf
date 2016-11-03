package com.huolicai.android.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.improving.utils.tools.AndroidBugsSolution;
import android.improving.utils.views.CommonEditView;
import android.improving.utils.views.circleprogressbutton.CircularCountDownButton;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.common.Constants;
import com.huolicai.android.presenter.register.RegisterPresenter;
import com.huolicai.android.utils.AnimationUtils;
import com.huolicai.android.utils.Utils;
import com.huolicai.android.view.register.IRegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 16/4/13 下午3:50
 * Email：yujunyao@yonglibao.com
 */

public class RegisterActivity extends BaseActivity implements IRegisterView{

    @Bind(R.id.register_count_down)
    CircularCountDownButton registerCountDown;
    @Bind(R.id.register_top_logo)
    ImageView registerTopLogo;
    @Bind(R.id.register_input_account_logo)
    ImageView registerInputAccountLogo;
    @Bind(R.id.register_input_account)
    CommonEditView registerInputAccount;
    @Bind(R.id.register_input_password_logo)
    ImageView registerInputPasswordLogo;
    @Bind(R.id.register_input_vcode)
    CommonEditView registerInputVCode;
    @Bind(R.id.register_btn)
    Button registerBtn;
    @Bind(R.id.register_input_ll)
    LinearLayout registerInputLl;
    @Bind(R.id.register_agreement)
    TextView registerAgreement;

    private RegisterPresenter registerPresenter;
    private Bitmap bitmap = null;
    private static final int ACCOUNT_INPUT_TYPE = 1;
    private static final int VCODE_INPUT_TYPE = 2;

    public static void startActivity(Activity mActivity) {
        Intent intent = new Intent(mActivity, RegisterActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    protected String currActivityName() {
        return Utils.id2Str(this, R.string.activity_register);
    }

    @Override
    protected void initSubView() {
        getAppBar().setVisibility(View.GONE);
        registerInputAccount.setOnFocusCommonChangeListener(new OnFocusChangeListenerImp(ACCOUNT_INPUT_TYPE));
        registerInputVCode.setOnFocusCommonChangeListener(new OnFocusChangeListenerImp(VCODE_INPUT_TYPE));
        registerInputAccount.setOnTextChangeListener(new OnTextChangeListenerImp());
        registerInputVCode.setOnTextChangeListener(new OnTextChangeListenerImp());
    }

    @OnClick({R.id.register_btn, R.id.register_agreement, R.id.register_back_app, R.id.register_around})
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register_btn:
                break;
            case R.id.register_agreement:
                break;
            case R.id.register_back_app:
                break;
            case R.id.register_around:
                break;
        }
    }

    @Override
    protected void initSubData() {
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.buttonDisable();
        registerAgreement.setText(Html.fromHtml(Utils.id2Str(mContext, R.string.text_agreement)));
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo);
        registerCountDown.setCountTextColor(Color.GRAY);
        registerCountDown.setInditorColor(R.color.transpanret);
        registerCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送验证码成功后开始计时
                registerCountDown.setInditorColor(R.color.color_f8364e);
                registerCountDown.startCountDown(Constants.COUNT_DOWN_TIME_120);
            }
        });
        registerCountDown.setCountDownListener(new CircularCountDownButton.ViewCountDownListener() {
            @Override
            public void start() {

            }

            @Override
            public void finish() {

            }
        });
        AndroidBugsSolution.assistActivity(this, new AndroidBugsSolution.OnKeyboardListener() {
            @Override
            public void onKeyboardChanged(int state) {
                switch(state) {
                    case AndroidBugsSolution.SHOW_KEYBOARD:
                        AnimationUtils.setLoginLogoScale(registerTopLogo, 1, 0);
                        AnimationUtils.setLoginInputUp(registerInputLl, 0, 0 - bitmap.getHeight());
                        break;
                    case AndroidBugsSolution.HIDE_KEYBOARD:
                        AnimationUtils.setLoginLogoScale(registerTopLogo, 0, 1);
                        AnimationUtils.setLoginInputUp(registerInputLl, 0 - bitmap.getHeight(), 0);
                        break;
                }
            }
        });
    }

    @Override
    public void accountFocusEvent(boolean bFlag) {
        if(bFlag) {
            registerInputAccountLogo.setImageResource(R.mipmap.icon_phone_sel);
        }else {
            registerInputAccountLogo.setImageResource(R.mipmap.icon_phone_unsel);
        }
    }

    @Override
    public void vcodeFocusEvent(boolean bFlag) {
        if(bFlag) {
            registerInputPasswordLogo.setImageResource(R.mipmap.icon_password_sel);
        }else {
            registerInputPasswordLogo.setImageResource(R.mipmap.icon_password_unsel);
        }
    }

    @Override
    public void buttonEnable() {
        registerBtn.setTextColor(getResources().getColor(R.color.white));
        registerBtn.setEnabled(true);
    }

    @Override
    public void buttonDisable() {
        registerBtn.setTextColor(getResources().getColor(R.color.color_fd9aa7));
        registerBtn.setEnabled(false);
    }

    private class OnFocusChangeListenerImp implements CommonEditView.OnFocusCommonChangeListener {
        private int iType;
        private OnFocusChangeListenerImp(int iType) {
            this.iType = iType;
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            if(iType == ACCOUNT_INPUT_TYPE) {
                registerPresenter.accountFocusEvent(b);
            }else if(iType == VCODE_INPUT_TYPE) {
                registerPresenter.vcodeFocusEvent(b);
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
            if(!TextUtils.isEmpty(registerInputAccount.getText().toString()) && !TextUtils.isEmpty(registerInputVCode.getText().toString())) {
                registerPresenter.buttonEnable();
            }else {
                registerPresenter.buttonDisable();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerCountDown.cancel();
    }

}
