package com.huolicai.android.activity.home;

import android.content.Intent;
import android.improving.utils.tools.countdown.CountDownAction;
import android.improving.utils.tools.countdown.CountDownListener;
import android.improving.utils.views.risenumber.RiseNumberTextView;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.huolicai.android.R;
import com.huolicai.android.base.BaseActivity;
import com.huolicai.android.bean.Sum;
import com.huolicai.android.utils.LogUtil;

/**
 * Auth：yujunyao
 * Since: 2016/3/15 17:50
 * Email：yujunyao@yonglibao.com
 */
public class HomeActivity extends BaseActivity{

    private TextView textView;
    private CountDownAction countDownAction;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        RiseNumberTextView text = (RiseNumberTextView) findViewById(R.id.rise);
        text.withNumber(56.10f);

        text.setDuration(5000);
        text.start();

        textView = (TextView) findViewById(R.id.aaaaa);
        countDownAction = new CountDownAction(60000, 1000, new CountDownListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("" + millisUntilFinished);
                LogUtil.iLogPrint("millisUntilFinished", millisUntilFinished + "");
            }

            @Override
            public void onFinish() {

            }
        });
        countDownAction.onStart();

    }

    @Override
    protected String currActivityName() {
        return null;
    }

    @Override
    protected void initSubView() {

    }

    @Override
    protected void initSubData() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownAction.onCancel();
    }

    @Override
    public void bindData(Object object) {
        super.bindData(object);
        Log.e("home" , "test --- "+((Sum)object).sum);
    }
}
