package com.huolicai.android.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Auth：yujunyao
 * Since: 2016/3/8 17:40
 * Email：yujunyao@yonglibao.com
 */
public class RequestParams {


    /**
     * 基本信息
     * @return
     */
    private static Map<String , String> getBase(){
        Map<String, String> params = new HashMap<>();
        params.put("token" , "12345666");
        params.put("version" , "12345666");
        return params;
    }

    /***
     * case
     * @return
     */
    public static Map<String, String> addSum() {
        Map<String, String> params = getBase();
        params.put("u", "dsaffsgdsgfgfgdfg");
        return params;
    }

}
