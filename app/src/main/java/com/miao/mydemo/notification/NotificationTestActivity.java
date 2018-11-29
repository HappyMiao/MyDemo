package com.miao.mydemo.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.miao.mydemo.R;

public class NotificationTestActivity extends AppCompatActivity implements View.OnClickListener{

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                sendNotification();
                break;
        }
    }

    private void sendNotification(){
        //适配 Android 8.0 的通知
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Notification notification = new Notification.Builder(this)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                    .setContentTitle("一条新通知")
//                    .setContentText("这是一条测试消息")
//                    .setAutoCancel(true)
//                    .build();
//            notificationManager.notify(1, notification);
//        }
    }
}
