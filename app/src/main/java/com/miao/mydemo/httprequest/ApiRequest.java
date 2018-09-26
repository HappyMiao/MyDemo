package com.miao.mydemo.httprequest;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @description: 创建Retrofit实例，提供创建接口Service的窗口
 * @author: miao
 * @time: 2018-09-25
 */
public class ApiRequest {

    //请求HOST
    private final static String REQUEST_HOST = "http://www.wanandroid.com/";
    private Retrofit retrofit;
    private static ApiRequest apiRequestInstance;

    /**
     * 获取ApiRequest单例
     */
    public static ApiRequest getApiRequestInstance(){
        if(apiRequestInstance == null){
            apiRequestInstance = new ApiRequest();
        }
        return apiRequestInstance;
    }

    /**
     * 构造，创建Retrofit
     */
    private ApiRequest(){
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(logInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(REQUEST_HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 创建APIService实例
     */
    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
