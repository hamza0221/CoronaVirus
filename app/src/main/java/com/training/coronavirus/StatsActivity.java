package com.training.coronavirus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.race604.drawable.wave.WaveDrawable;

public class StatsActivity extends BaseActivity implements View.OnClickListener, DialogFragmentAdvices.OnFragmentInteractionListener, RewardedVideoAdListener {
    TextView textAdvices;
    ImageView heartImage;
    private WaveDrawable mWaveDrawable;
    private int score, count_size;
    TextView textResultLovePercent;
    TextView repeat;
    TextView tvshare;
    private int UnityScore = 5;
    static final String SCORE_KEY = "SCORE_KEY";
    String finaScore;
    private RewardedVideoAd mRewardedVideoAd;
    boolean rewarded;

    //this will be the app link in the future


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        repeat = (TextView) findViewById(R.id.repeat);
        repeat.setOnClickListener(this);
        textAdvices = (TextView) findViewById(R.id.textAdvices);
        textAdvices.setOnClickListener(this);
        tvshare = (TextView) findViewById(R.id.share);
        tvshare.setOnClickListener(this);
        textResultLovePercent = (TextView) findViewById(R.id.textResultLovePercent);
        heartImage = (ImageView) findViewById(R.id.imageheart);
        mWaveDrawable = new WaveDrawable(this, R.drawable.logo_app);
        heartImage.setImageDrawable(mWaveDrawable);

        if (getIntent().getExtras() != null) {

            score = getIntent().getExtras().getInt(QuestionActivity.SCORE_KEY);
            Log.d(" received score", String.valueOf(score));


            count_size = getIntent().getExtras().getInt(QuestionActivity.QUESTIONS_SIZE_KEY);
            Log.d(" count_size", String.valueOf(count_size));

        }
        Log.d(" CalculatePercent", String.valueOf(CalculatePercent(score)));
        SetWaveValues();

        finaScore = String.valueOf((int) CalculatePercent(score));
        textResultLovePercent.setText(finaScore + " " + "بالمئة");
        setupAdAtBottom();
        MobileAds.initialize(this, IDSads.ID_APP_ADMOB);
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);


        loadRewardedVideoAd();
    }
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(IDSads.ID_REWARDED_ADMOB,
                new AdRequest.Builder().build());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textAdvices:
              /*  rewarded =false;
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
                    else{
                        loadRewardedVideoAd();
                }*/
                ShowResult();


                break;
            case R.id.repeat:
                Intent intent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.share:
                Log.e("share", "share");
                Intent intentshare = new Intent();
                intentshare.setAction(Intent.ACTION_SEND);
                intentshare.setType("text/plain");
                intentshare.putExtra(Intent.EXTRA_TEXT, "نسبة مرضي بفيروس الكورونا حسب الإختبار هي " + finaScore + " بالمئة " + getResources().getText(R.string.linkApp));
                startActivity(Intent.createChooser(intentshare, "Share"));
                break;


        }

    }
    void ShowResult(){
        DialogFragmentAdvices dialogFragmentAdvices = new DialogFragmentAdvices();
        Bundle args = new Bundle();
        args.putInt(SCORE_KEY, (int) CalculatePercent(score));
        dialogFragmentAdvices.setArguments(args);
        dialogFragmentAdvices.show(getSupportFragmentManager(), "dialogFragmentAdvices");


    }

    void SetWaveValues() {
        Log.e("setLevel", String.valueOf((int) CalculatePercent(score) * 100));
        mWaveDrawable.setLevel((int) CalculatePercent(score) * 80);
        mWaveDrawable.setWaveAmplitude(7);
        mWaveDrawable.setWaveLength(276);
        mWaveDrawable.setWaveSpeed(9);//9
        mWaveDrawable.setIndeterminate(false);


    }

    double CalculatePercent(int scoreValue) {
        return (scoreValue / (float) (count_size * UnityScore)) * 100;


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        //if (rewarded)
           // ShowResult();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        loadRewardedVideoAd();


    }

    @Override
    public void onRewardedVideoCompleted() {
        rewarded =true;

    }
}
