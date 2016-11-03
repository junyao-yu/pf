package com.huolicai.android.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.improving.utils.views.circleprogressbutton.CircularCountDownButton;
import android.improving.utils.views.editinputlayout.MaterialEditText;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.common.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaofei on 2016/4/14.
 */
public class ValidatePhoneActivity extends BaseActivity {

    @Bind(R.id.edit_phone)
    MaterialEditText editPhone;
    @Bind(R.id.edit_delete)
    ImageView editDelete;
    @Bind(R.id.edit_validate_code)
    MaterialEditText editValidateCode;
    @Bind(R.id.validate_count_down)
    CircularCountDownButton validateCountDown;
    @Bind(R.id.validate_line)
    View validateLine;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_validata_phone);
        ButterKnife.bind(this);

    }

    @Override
    protected String currActivityName() {
        return getString(R.string.activity_validate_phone_title);
    }

    @Override
    protected void initSubView() {
        validateCountDown.setCountTextColor(Color.GRAY);
        validateCountDown.setInditorColor(R.color.transpanret);
        validateCountDown.setIsTranstionX(true);
        validateCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送验证码成功后开始计时
                validateLine.setVisibility(View.GONE);
                validateCountDown.setInditorColor(R.color.color_f8364e);
                validateCountDown.startCountDown(Constants.COUNT_DOWN_TIME_60);
            }
        });
        validateCountDown.setCountDownListener(new CircularCountDownButton.ViewCountDownListener() {
            @Override
            public void start() {
            }

            @Override
            public void finish() {
                validateLine.setVisibility(View.VISIBLE);
            }
        });

        editDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPhone.setText("");
            }
        });

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s.toString())) {
                    editDelete.setVisibility(View.GONE);
                } else {
                    editDelete.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initSubData() {

    }

}
