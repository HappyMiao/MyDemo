package com.miao.mydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miao.mydemo.coordinatorlayout.CoordinatorLayoutDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1  = findViewById(R.id.button1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, CoordinatorLayoutDemoActivity.class));
                break;
        }
    }
}
