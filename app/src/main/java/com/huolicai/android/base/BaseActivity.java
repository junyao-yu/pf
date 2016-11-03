package com.huolicai.android.base;

import android.content.Context;
import android.content.Intent;
import android.improving.utils.tools.StatusBarCompat;
import android.improving.utils.views.AppBar;
import android.improving.utils.views.swipeback.SwipeBackActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.huolicai.android.R;
import com.huolicai.android.presenter.BasePresenter;
import com.huolicai.android.view.IBaseView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * Auth：yujunyao
 * Since: 2016/3/8 17:15
 * Email：yujunyao@yonglibao.com
 *
 * 如果model和view不是频繁的交互 , 只是为了model数据显示在view上 , 可以不需要重新建model  presenter 及 view类 , 直接使用basepresenter操作
 * baseActivity中的basepresenter是public的,直接用来请求数据就可以了, 如果和网络无关的model和view的交互 ,新建的model presenter不用继承baseModel 及 BasePresenter
 */
public abstract class BaseActivity extends SwipeBackActivity implements IBaseView {

    private FrameLayout baseContainer;
    public Context mContext;
    private AppBar appBar;

    public BasePresenter basePresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        initBaseView();
        initBaseData();

        if (null != getIntent()) {
            onInitParams(getIntent());
        }

        setupViews(savedInstanceState);

        initSubView();
        initSubData();
    }

    /**
     * 初始化接收到的的参数，在 setupViews 之前执行，在继承类中实现其功能
     */
    protected abstract void onInitParams(Intent intent);

    /**
     * 初始化页面视图 此函数在onCreate后被调用
     */
    protected abstract void setupViews(Bundle savedInstanceState);

    /**描述当前页面的title--便于友盟统计*/
    protected abstract String currActivityName();

    protected abstract void initSubView();

    protected abstract void initSubData();


    protected void initBaseView(){
        baseContainer = (FrameLayout) findViewById(R.id.base_container);
        appBar = (AppBar)findViewById(R.id.app_bar);
        controlAppBar(appBar);
        setAppBarHeight();
        appBar.setSupportActionBar(this);
        StatusBarCompat.compat(this);
        appBar.setBackImage(R.mipmap.back);
        appBar.setImageBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
        appBar.setTitle(currActivityName());
        mContext = this;
    }

    protected void finishActivity() {
        finish();
    }

    protected void backActivity() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            backActivity();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void initBaseData() {
        basePresenter = new BasePresenter(this);
        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);
        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, baseContainer);
    }

    /**
     * set app bar height
     */
    private void setAppBarHeight(){
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        ViewGroup.LayoutParams params = appBar.getLayoutParams();
        params.height  = StatusBarCompat.getStatusBarHeight(this) + actionBarHeight;
        appBar.setLayoutParams(params);
    }

    /**
     *  control appbar ,  如果需要修改appbar 请重写该方法
     * @param appbar
     */
    public void controlAppBar(AppBar appbar){

    }

    /**
     * get appbar
     * @return
     */
    public AppBar getAppBar(){
        return appBar;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
        MobclickAgent.onPageStart(currActivityName());
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
        MobclickAgent.onPageEnd(currActivityName());
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.cancelRequest();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void bindData(Object object) {

    }
}
