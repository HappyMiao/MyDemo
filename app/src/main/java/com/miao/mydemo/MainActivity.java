package com.miao.mydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miao.mydemo.aidlclient.AIDLClientActivity;
import com.miao.mydemo.coordinatorlayout.CoordinatorLayoutDemoActivity;
import com.miao.mydemo.httprequest.HttpTestActivity;
import com.miao.mydemo.kotlin.KotlinDemoActivity;
import com.miao.mydemo.notification.NotificationTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, CoordinatorLayoutDemoActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, HttpTestActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, NotificationTestActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, KotlinDemoActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, AIDLClientActivity.class));
                break;
        }
    }
}
