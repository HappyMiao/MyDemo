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

/**
 * @description: 网络请求框架封装探索 基本请求、
 *               数据ResponseBody转换、拦截打印请求内容、
 *               添加头部信息、cookie持久化存储自动登录验证
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
                ApiTest1 apiTest1 = new ApiTest1();
                apiTest1.getResponse(new RequestCallBack<Test1>() {

                    @Override
                    public void onSuccess(Test1 response) {
                        if(!TextUtils.isEmpty(response.getData().get(0).getImagePath())){
                            Glide.with(HttpTestActivity.this)
                                    .load(response.getData().get(0).getImagePath())
                                    .into(imageView);
                        }
                        if(!TextUtils.isEmpty(response.getData().get(0).getTitle())){
                            textView.setText(response.getData().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onError(String err_msg) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
        }
    }
}
