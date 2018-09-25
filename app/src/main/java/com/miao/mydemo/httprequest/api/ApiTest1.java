package com.miao.mydemo.httprequest.api;

import android.widget.Toast;

import com.miao.mydemo.httprequest.ApiRequest;
import com.miao.mydemo.httprequest.ApiService;
import com.miao.mydemo.httprequest.HttpTestActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description: 具体接口请求测试1
 * @author: miao
 * @time: 2018-09-25
 */
public class ApiTest1 {

    public void getResponse(){
        //创建APIService实例
        ApiService.HttpTestService httpTestService =
                ApiRequest.getApiRequestInstance().create(ApiService.HttpTestService.class);
        //创建请求，请求执行
        Call<ResponseBody> call = httpTestService.getJSON();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(HttpTestActivity.this,
                            "onResponse 返回成功 数据打印在Logcat", Toast.LENGTH_SHORT).show();
                    System.out.println("mzzzzzz " + response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
