package com.huolicai.android.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huolicai.android.R;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午2:08
 * Email：yujunyao@yonglibao.com
 */

public class ToastUtil {

    private static Toast toast = null;

    public static void showToast(Context context, String tips) {
        if (TextUtils.isEmpty(tips)) {
            return;
        }
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.toast_layout, null);
            TextView textView = (TextView) view
                    .findViewById(R.id.toast_message);
            textView.setText(tips);
            if (toast == null) {
                toast = new Toast(context);
            }
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(Context context, int resourceID) {
        showToast(context, context.getResources().getString(resourceID));
    }


}
