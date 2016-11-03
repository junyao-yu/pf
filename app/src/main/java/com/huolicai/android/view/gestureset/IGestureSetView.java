package com.huolicai.android.view.gestureset;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午1:39
 * Email：yujunyao@yonglibao.com
 */

public interface IGestureSetView {

    void showWrongTips();

    void showRightTips();

    void updateIndicate(String password);

    void resetGesture();

}
