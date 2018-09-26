package com.miao.mydemo.httprequest;

import com.miao.mydemo.httprequest.model.Test1;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @description: ApiService 接口定义
 * @author: miao
 * @time: 2018-09-25
 */
public class ApiService {

    //无参数示例
    public interface HttpTestService {
        @GET("banner/json")
        Call<Test1> getJSON();
    }

    //有参数示例
    public interface HttpTestService2 {
        @GET("article/list/{page}/json")
        Call<ResponseBody> getJSON(@Path("page") int page);
    }
}
