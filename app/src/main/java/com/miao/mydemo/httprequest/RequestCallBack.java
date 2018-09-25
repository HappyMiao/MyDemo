package com.miao.mydemo.httprequest;

/**
 * @description: RequestCallBack View层实际调用接口后，把框架处理完的具体数据返回给view层。
 * @author: miao
 * @time: 2018-09-25
 */
public interface RequestCallBack<T> {
    //请求数据成功
    void onSuccess(T response);
    //请求数据错误
    void onError(String err_msg);
    //网络请求失败
    void onFailure();
}
