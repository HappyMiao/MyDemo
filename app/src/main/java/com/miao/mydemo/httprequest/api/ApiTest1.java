package com.miao.mydemo.httprequest.api;

import android.widget.Toast;

import com.miao.mydemo.httprequest.ApiCallBack;
import com.miao.mydemo.httprequest.ApiRequest;
import com.miao.mydemo.httprequest.ApiService;
import com.miao.mydemo.httprequest.RequestCallBack;
import com.miao.mydemo.httprequest.model.Test1;

import retrofit2.Call;

/**
 * @description: 具体接口请求测试1
 * @author: miao
 * @time: 2018-09-25
 */
public class ApiTest1 {

    public void getResponse(RequestCallBack<Test1> requestCallBack){
        //创建APIService实例
        ApiService.HttpTestService httpTestService =
                ApiRequest.getApiRequestInstance().create(ApiService.HttpTestService.class);
        //创建请求，请求执行
        Call<Test1> call = httpTestService.getJSON();
        call.enqueue(new ApiCallBack<>(requestCallBack));
    }
}
