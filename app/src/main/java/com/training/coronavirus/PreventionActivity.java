package com.training.coronavirus;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;

public class PreventionActivity extends BaseActivity {
ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);

constraintLayout=findViewById(R.id.prevention_container);
constraintLayout.addView(Singleton.myWebViewPrevention);

        setupAdAtBottom();




    }


    @Override
    public void onBackPressed() {
       constraintLayout.removeView(Singleton.myWebViewPrevention);
        super.onBackPressed();
    }
}
