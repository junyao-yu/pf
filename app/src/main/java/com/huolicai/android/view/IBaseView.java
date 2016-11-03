package com.huolicai.android.view;

/**
 * Auth：yujunyao
 * Since: 2016/3/30 11:13
 * Email：yujunyao@yonglibao.com
 *
 * 带有网络请求的view需要继承该类,
 */
public interface IBaseView {

    void bindData(Object object);

    void showProgress();

    void hideProgress();

}
