package com.miao.mydemo.httprequest;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @description: 拦截器 打印请求URL、添加请求头部信息等
 * @author: miao
 * @time: 2018-09-26
 */
public class RequestInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {

        //拦截请求，添加请求头
        Request request = chain.request().newBuilder()
                .addHeader("token", "SSSSSS")
                .addHeader("par2", "112233445566")
                .addHeader("par3", "hahaha")
                .build();

        //打印请求URL
        //String requestStartMessage = "--> " + request.method() + ' ' + request.url();
        //System.out.println("mzzzzzz OkHttp Request URL:---" + requestStartMessage);
        //System.out.println("mzzzzzz OkHttp Request Headers:---" + request.headers().toString());

        //获取响应内容
        //Response response = chain.proceed(request);
        //System.out.println("mzzzzzz OkHttp Request Response:---"+response.body().string());

        return chain.proceed(request);
    }
}
