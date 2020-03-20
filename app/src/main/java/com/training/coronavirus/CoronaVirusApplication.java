package com.training.coronavirus;

import android.app.Application;
import android.webkit.WebView;

public class CoronaVirusApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        Singleton.myWebView=new WebView(this);
        Singleton.setUpWebView();

    }
}
