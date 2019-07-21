package com.miao.mydemo.httprequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miao.mydemo.R;
import com.miao.mydemo.httprequest.api.ApiTest1;
import com.miao.mydemo.httprequest.model.Test1;
import com.miao.mydemo.rxjavaretrofit.ApiTest;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 网络请求框架封装探索
 * 1、基本请求。
 * 2、数据ResponseBody转换为需要的实体。
 * 3、拦截打印请求内容、添加头部信息，如需要cookie持久化存储自动登录验证的接口。
 * 框架说明
 * 1、ApiRequest 创建Retrofit实例，为基本请求打下基础。
 * 2、ApiService 定义基本的接口，各种类型的接口
 * 3、RequestCallBack 数据回调的接口，用于将数据回调给发起请求的地方。
 * 4、ApiCallBack 实现 Callback<T>接口，这里面处理返回的数据。RequestCallBack接口在这里面回调。
 * 5、RequestInterceptor 请求拦截器，拦截请求，添加Header，用于免登陆等类型操作。
 * 6、api-ApiTest1 具体的请求封装，界面层调用他发起请求
 * 7、model--请求返回的数据实体。
 * @author: miao
 * @time: 2018-09-25
 */
public class HttpTestActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpdemo);
        btn1  = findViewById(R.id.button1);
        imageView  = findViewById(R.id.imageView);
        textView  = findViewById(R.id.textView);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
//                ApiTest1 apiTest1 = new ApiTest1();
//                apiTest1.getResponse(new RequestCallBack<Test1>() {
//
//                    @Override
//                    public void onSuccess(Test1 response) {
//                        if(!TextUtils.isEmpty(response.getData().get(0).getImagePath())){
//                            Glide.with(HttpTestActivity.this)
//                                    .load(response.getData().get(0).getImagePath())
//                                    .into(imageView);
//                        }
//                        if(!TextUtils.isEmpty(response.getData().get(0).getTitle())){
//                            textView.setText(response.getData().get(0).getTitle());
//                        }
//                    }
//
//                    @Override
//                    public void onError(String err_msg) {}
//
//                    @Override
//                    public void onFailure() { }
//                });
//                break;
                ApiTest apiTest = new ApiTest();
                apiTest.getResponse().subscribe(new Observer<Test1>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Test1 test1) {
                        if(!TextUtils.isEmpty(test1.getData().get(0).getImagePath())){
                            Glide.with(HttpTestActivity.this)
                                    .load(test1.getData().get(0).getImagePath())
                                    .into(imageView);
                        }
                        if(!TextUtils.isEmpty(test1.getData().get(0).getTitle())){
                            textView.setText(test1.getData().get(0).getTitle()+"-- rxjava");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }
    }
}
