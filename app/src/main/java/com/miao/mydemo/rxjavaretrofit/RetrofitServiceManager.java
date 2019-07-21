package com.miao.mydemo.rxjavaretrofit;


import com.miao.mydemo.httprequest.RequestInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @description: 创建Retrofit实例
 * @author: miao
 * @time: 2019-07-20
 */
public class RetrofitServiceManager {

    private final static String REQUEST_HOST = "http://www.wanandroid.com/";
    private Retrofit retrofit;
    private static RetrofitServiceManager apiRequestInstance;

    public static RetrofitServiceManager getInstance(){
        if(apiRequestInstance == null){
            apiRequestInstance = new RetrofitServiceManager();
        }
        return apiRequestInstance;
    }

    private RetrofitServiceManager(){
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(logInterceptor)
                //.addInterceptor(new RequestInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(REQUEST_HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
