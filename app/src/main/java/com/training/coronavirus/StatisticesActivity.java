package com.training.coronavirus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class StatisticesActivity extends BaseActivity {
ConstraintLayout constraintLayout;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistices);
constraintLayout=findViewById(R.id.webview_layout);
constraintLayout.addView(Singleton.myWebView);
        setupAdAtBottom();




    }


    @Override
    public void onBackPressed() {
        constraintLayout.removeView(Singleton.myWebView);
        super.onBackPressed();


    }
}
