package com.huolicai.android.view.login;

import com.huolicai.android.view.IBaseView;

/**
 * Auth：yujunyao
 * Since: 2016/3/30 11:13
 * Email：yujunyao@yonglibao.com
 */
public interface ILoginView extends IBaseView{

    void accountFocusEvent(boolean bFlag);

    void passwordFocusEvent(boolean bFlag);

    void buttonEnable();

    void buttonDisable();

}
