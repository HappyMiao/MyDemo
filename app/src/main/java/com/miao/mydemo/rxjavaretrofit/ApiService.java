package com.miao.mydemo.rxjavaretrofit;

import com.miao.mydemo.httprequest.model.Test1;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @description: ApiService 接口定义
 * @author: miao
 * @time: 2019-07-20
 */
public class ApiService {

    //无参数示例
    public interface HttpTestService {
        @GET("banner/json")
        Observable<Test1> getJSON();
    }
}
