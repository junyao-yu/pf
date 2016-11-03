package com.huolicai.android.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gaofei on 2016/4/8.
 */
public class MessageBean {

    @SerializedName(value = "r")
    public String title;

    @SerializedName(value = "r")
    public String content;

    @SerializedName(value = "r")
    public String time;

    @SerializedName(value = "r")
    public String hasRead;


}
