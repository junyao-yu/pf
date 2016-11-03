package com.huolicai.android.presenter;

import com.android.volley.VolleyError;
import com.huolicai.android.interfaces.RequestListener;
import com.huolicai.android.model.BaseModelImp;
import com.huolicai.android.view.IBaseView;

import java.util.Map;

/**
 * Auth：yujunyao
 * Since: 2016/3/30 11:20
 * Email：yujunyao@yonglibao.com
 *
 * 带有网络请求的presenter需要继承该类
 */
public class BasePresenter implements RequestListener {

    private IBaseView mBaseView;

    private BaseModelImp mBaseModel;

    public <T extends IBaseView> BasePresenter(T view) {
        mBaseView = view;
        mBaseModel = new BaseModelImp();
        mBaseModel.setRequestListener(this);
    }

    public void reqeustData(String url, Map<String, String> bodyParam, Object obj, int requestType){
        mBaseView.showProgress();
        mBaseModel.requestData(url, bodyParam, obj, requestType);
    }

    public void cancelRequest(){
        mBaseModel.cancelRequest();
    }

    @Override
    public void requestFail(VolleyError volleyError) {
        mBaseView.hideProgress();
    }

    @Override
    public void requestSuccess(Object object) {
        mBaseView.hideProgress();
        mBaseView.bindData(object);
    }

}
