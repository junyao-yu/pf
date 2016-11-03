package com.huolicai.android.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by gaofei on 2016/4/12.
 */
public interface RequestListener {

    void requestSuccess(Object object);

    void requestFail(VolleyError volleyError);
}
