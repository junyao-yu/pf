package com.huolicai.android.view.register;

import com.huolicai.android.view.IBaseView;

/**
 * Auth：yujunyao
 * Since: 2016/4/14 14:27
 * Email：yujunyao@yonglibao.com
 */
public interface IRegisterView extends IBaseView {

    void accountFocusEvent(boolean bFlag);

    void vcodeFocusEvent(boolean bFlag);

    void buttonEnable();

    void buttonDisable();

}
