package com.huolicai.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午2:35
 * Email：yujunyao@yonglibao.com
 */

public class Utils {

    public static String id2Str(Context context, int resourceID) {
        return context.getResources().getString(resourceID);
    }

    public static String id2Str(Context context, int resourceID, int param1) {
        return context.getResources().getString(resourceID, param1);
    }

    public static boolean strCompare(String str1, String str2) {
        if(TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if(str1.equals(str2)) {
            return true;
        }
        return false;
    }

    public static void bitmapRecycle(Bitmap bitmap) {
        if(bitmap != null) {
            bitmap.recycle();
        }
    }

}
