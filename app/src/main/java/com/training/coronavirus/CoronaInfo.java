package com.training.coronavirus;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;

public class CoronaInfo extends BaseActivity {
ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_info);
        constraintLayout=findViewById(R.id.info_webview);
        constraintLayout.addView(Singleton.myWebViewInformation);


        setupAdAtBottom();







    }

    @Override
    public void onBackPressed() {
      constraintLayout.removeView(Singleton.myWebViewInformation);
        super.onBackPressed();
    }
}
