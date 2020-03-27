package com.training.coronavirus;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    private FrameLayout content;
    //AdView adView = new AdView(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });*/
       // setupAdAtBottom();

    }



    private void setSpaceForAd(int height) {

        // content.getChildAt(0).setPadding(0, 0, 0, 50);
        View child0 = content.getChildAt(0);
        FrameLayout.LayoutParams layoutparams = (FrameLayout.LayoutParams) child0
                .getLayoutParams();
        layoutparams.bottomMargin = height;
        child0.setLayoutParams(layoutparams);

    }

    @SuppressLint("NewApi")
    protected void setupAdAtBottom() {
        content = (FrameLayout) findViewById(android.R.id.content);
        // inflate ad layout and set it to bottom by layouparams
        final LinearLayout ad = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.ad_layout, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        ad.setLayoutParams(params);

        // adding viewtreeobserver to get height of ad layout , so that
        // android.R.id.content will set margin of that height
        ViewTreeObserver vto = ad.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    ad.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    ad.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int width = ad.getMeasuredWidth();
                int height = ad.getMeasuredHeight();
                Log.i("ad hight", height + "");
                setSpaceForAd(height);

            }

        });
        addLayoutToContent(ad);



    }

    private void addLayoutToContent(View ad) {
        // content.addView(ad);
        content.addView(ad);
        AdView mAdView = (AdView) ad.findViewById(R.id.adView);
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder()./*addTestDevice("25A08F96A139A6963CF40FF3CBFA0D62").*/build();
        //RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("25A08F96A139A6963CF40FF3CBFA0D62");

        mAdView.loadAd(adRequest);
    }

}
