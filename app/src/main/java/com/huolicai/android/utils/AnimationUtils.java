package com.huolicai.android.utils;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Auth：yujunyao
 * Since: 16/4/12 上午9:52
 * Email：yujunyao@yonglibao.com
 */

public class AnimationUtils {

    /**
     * 设置手势错误提示抖动
     * @param view
     */
    public static void setGestureTipsShake(View view) {
        ObjectAnimator.ofFloat(view, "translationX", -50, 100, 0).setDuration(50).start();
    }

    /**
     * 设置登录界面logo缩放
     * @param view
     */
    public static void setLoginLogoScale(View view, int begin, int end) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", begin, end),
                ObjectAnimator.ofFloat(view, "scaleY", begin, end)
        );
        set.setDuration(1000).start();
    }

    /**
     *设置登录界面输入布局往上平移
     */
    public static void setLoginInputUp(View view, int begin, int end) {
        ObjectAnimator.ofFloat(view, "translationY",begin, end).setDuration(1000).start();
    }

}
