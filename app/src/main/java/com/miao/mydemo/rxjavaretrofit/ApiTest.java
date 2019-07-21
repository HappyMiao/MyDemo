package com.miao.mydemo.rxjavaretrofit;

import com.miao.mydemo.httprequest.model.Test1;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @description: 具体接口请求测试
 * @author: miao
 * @time: 2019-07-20
 */
public class ApiTest {

    public Observable<Test1> getResponse(){
        ApiService.HttpTestService httpTestService =
                RetrofitServiceManager.getInstance().create(ApiService.HttpTestService.class);
        return observe(httpTestService.getJSON());
    }

    private <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
