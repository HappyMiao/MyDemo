package com.miao.mydemo.httprequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description: 自定义enqueue(Callback<T> callback)的Callback
 * @author: miao
 * @time: 2018-09-26
 */
public class ApiCallBack<T> implements Callback<T>{

    private RequestCallBack<T> requestCallBack;

    public ApiCallBack(RequestCallBack<T> requestCallBack){
        this.requestCallBack = requestCallBack;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        requestCallBack.onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        requestCallBack.onFailure();
    }
}
