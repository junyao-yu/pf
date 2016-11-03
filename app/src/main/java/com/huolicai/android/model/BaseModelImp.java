package com.huolicai.android.model;

import android.improving.utils.Init;
import android.improving.utils.http.NetRequest;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.huolicai.android.interfaces.RequestListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Auth：yujunyao
 * Since: 16/4/8 下午5:27
 * Email：yujunyao@yonglibao.com
 *
 * 带有网络请求的model需要继承该类
 */

public class BaseModelImp {

    private List<Integer> arrayTags = new ArrayList<>();
    private RequestListener mListener;

    public void requestData(String url, Map<String, String> bodyParam, Object obj, int requestType) {
        httpRequestEntrance(url , bodyParam ,obj ,requestType);
    }

    public void cancelRequest() {
        for(int tag : arrayTags) {
            Init.cancelRequest(tag);
        }
    }

    public void httpRequestEntrance(String url, Map<String, String> bodyParam, Object obj, int requestType) {

        NetRequest<Object> request = new NetRequest<>(Request.Method.POST, url, bodyParam, obj, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mListener.requestFail(volleyError);
            }
        }, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object object) {
                mListener.requestSuccess(object);
            }
        });
        arrayTags.add(requestType);
        Init.addRequest(request, requestType);
    }


    public void setRequestListener(RequestListener listener) {
        this.mListener = listener;
    }
}
