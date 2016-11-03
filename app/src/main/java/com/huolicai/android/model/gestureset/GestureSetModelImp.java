package com.huolicai.android.model.gestureset;

import android.improving.utils.Init;

import com.huolicai.android.common.SpKey;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午1:43
 * Email：yujunyao@yonglibao.com
 */

public class GestureSetModelImp implements IGestureSetModel {
    @Override
    public void setGesturePwd(String pwd) {
        Init.spUtil.setValues(SpKey.GESTURE_LOCK_PASSWORD, "");
    }
}
