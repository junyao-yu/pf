package com.huolicai.android.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.improving.utils.views.CommonEditView;
import android.improving.utils.tools.AppUtils;
import android.improving.utils.tools.DeviceID;
import android.improving.utils.tools.StringColorUtils;
import android.improving.utils.views.ExperienceProgress;
import android.improving.utils.views.calendarview.MaterialCalendarView;
import android.improving.utils.views.circleprogressbutton.CircularCountDownButton;
import android.improving.utils.views.yummytext.YummyTextSwitcher;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;

import com.huolicai.android.R;
import com.huolicai.android.activity.user.LoginActivity;
import com.huolicai.android.activity.user.RegisterActivity;
import com.huolicai.android.activity.user.ResetPwdActivity;
import com.huolicai.android.activity.user.ValidatePhoneActivity;
import com.huolicai.android.common.NumberFrameEvaluator;
import com.huolicai.android.utils.LogUtil;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Auth：yujunyao
 * Since: 2016/3/8 17:17
 * Email：yujunyao@yonglibao.com
 */
public class LaunchActivity extends FragmentActivity {


    @Bind(R.id.calendar_view)
    MaterialCalendarView calendarView;
    @Bind(R.id.experience_progress)
    ExperienceProgress experienceProgress;
    @Bind(R.id.commonEditView)
    CommonEditView commonEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);

        TextView textView = (TextView) findViewById(R.id.text);
        CharSequence s = StringColorUtils.from("This <is> what  , 这是支持<几种>颜色")
                .withSeparator("<>").innerColor(Color.RED)
                .outerColor(Color.GRAY).format();
        textView.setText(s);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPwdActivity.startActivity(LaunchActivity.this);
            }
        });

        commonEditView.setOnTextChangeListener(new CommonEditView.OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                LogUtil.eLogPrint("", "---------------onTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                LogUtil.eLogPrint("", "---------------beforeTextChanged");
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                LogUtil.eLogPrint("", "---------------afterTextChanged");

            }
        });

        commonEditView.setOnFocusCommonChangeListener(new CommonEditView.OnFocusCommonChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                LogUtil.eLogPrint("", "---------------onFocusChange  hasFocus=" + hasFocus);
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        calendarView.setSelectedDate(calendar.getTime());
//        通过实现DayViewDecorator 设置装饰 改变选中颜色、周末背景
//        calendarView.addDecorators(
//        设置今日 底色
//                new TodaySelectorDecorator(this),
//        设置 目标日期 底色
//                new TargetSelectorDecorator(this),
//        设置高亮
//                new HighlightWeekendsDecorator());

//        httpRequestEntrance("http://121.42.216.197:9888/V2/Project/tradeAmount", RequestParams.addSum(), new Sum(), 0);

        LogUtil.iLogPrint("", AppUtils.getAppName(this));
        LogUtil.iLogPrint("", AppUtils.getVersionName(this));
        LogUtil.iLogPrint("", AppUtils.getPackageName(this));
        LogUtil.iLogPrint("", AppUtils.getSignature(this));
        LogUtil.iLogPrint("device", DeviceID.getDeviceId(this));


        //teon
        final CircularCountDownButton circularButton1 = (CircularCountDownButton) findViewById(R.id.circularButton1);
        circularButton1.setIdleText("获取验证码");
        circularButton1.setCompleteText("获取验证码");
        circularButton1.setCountTextColor(Color.GRAY);
        circularButton1.setInditorColor(R.color.transpanret);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送验证码成功后开始计时
                circularButton1.setInditorColor(R.color.color_f8364e);
                circularButton1.startCountDown(60);
                startActivity(new Intent(LaunchActivity.this , ValidatePhoneActivity.class));
            }
        });
        circularButton1.setCountDownListener(new CircularCountDownButton.ViewCountDownListener() {
            @Override
            public void start() {
            }

            @Override
            public void finish() {
            }
        });


        YummyTextSwitcher yummyTextSwitcher = (YummyTextSwitcher) findViewById(R.id.yummyTextSwitcher);
        yummyTextSwitcher.setFrameInterpolator(new NumberFrameEvaluator(1, 3023));
        yummyTextSwitcher.startAnim();

        experienceProgress.setLvValue(1839);

        findViewById(R.id.go_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.startActivity(LaunchActivity.this);
            }
        });
        findViewById(R.id.go_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.startActivity(LaunchActivity.this);
            }
        });

    }


}
