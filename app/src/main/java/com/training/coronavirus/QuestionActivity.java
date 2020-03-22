package com.training.coronavirus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.training.coronavirus.BaseActivity;
import com.training.coronavirus.IDSads;
import com.training.coronavirus.Question;

import java.util.ArrayList;

public class QuestionActivity extends BaseActivity implements View.OnClickListener {
    ArrayList<Question> questionData = new ArrayList<Question>();
    private TextView question, questionNo;
    private RadioGroup rg;
    private TextView next;
    private Intent statsIntent;
    static final String SCORE_KEY = "SCORE_KEY";
    static final String QUESTIONS_SIZE_KEY = "QUESTIONS_SIZE_KEY";


    int k, radioId, i = 0, score = 0, count_size;
    private InterstitialAd mInterstitialAd;
    private AlertDialog noResponseDialog;
    private DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            noResponseDialog.dismiss();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = (TextView) findViewById(R.id.textQuestion);
        rg = (RadioGroup) findViewById(R.id.radiogroupChoices);
        questionNo = (TextView) findViewById(R.id.textQNo);
        next = (TextView) findViewById(R.id.btnNext);
        next.setOnClickListener(this);

        noResponseDialog=new AlertDialog.Builder(this).setPositiveButton("حسنا", dialogListener
        ).setMessage("يجب عليك اختيار اجابة").create();

        k = 0;

        statsIntent = new Intent(QuestionActivity.this, StatsActivity.class);
        statsIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (getIntent().getExtras() != null) {

            questionData = getIntent().getExtras().getParcelableArrayList(MainActivity.QUESTIONS_KEY);
            count_size = questionData.size();


            getQuestions(k);


        }
        setupAdAtBottom();

//load interstitial ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(IDSads.ID_INTERSTITIAL);



    }

    int getIndexofSelectedRadioButton() {

        int radioButtonID = rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(radioButtonID);
        int idx = rg.indexOfChild(radioButton);
        return idx;


    }

    public void getQuestions(int k) {
        // rg.setVisibility(View.INVISIBLE);
        rg.removeAllViews();


        // questionImage.setVisibility(View.INVISIBLE);

        question.setText(questionData.get(k).getText());

        questionNo.setText("السؤال " + (questionData.get(k).getId() + 1) + " من 8 أسئلةً");
        Log.d("listchoices", questionData.get(k).getChoicesList().toString());


        // new URLImage(this).execute(questionData.get(k).getImageURL());
        //set image with name
        // int id = getResources().getIdentifier(questionData.get(k).getImageURL(), "drawable", QuestionActivity.this.getPackageName());

        //   questionImage.setImageResource(id);


        // answer = questionData.get(k).getAnswer();
        for (int j = 0; j < questionData.get(k).getChoicesList().size(); j++) {
            RadioButton rb = new RadioButton(this);
            rb.setButtonDrawable(getResources().getDrawable(R.drawable.null_selector));
            rb.setBackground(getResources().getDrawable(R.drawable.my_selector));
            radioId = Integer.parseInt("1000" + (j + 1) + k);
            rb.setId(radioId);
            rb.setText("  "+questionData.get(k).getChoicesList().get(j).replace("\"",""));
            rb.setTextSize(30);
rb.setTextColor(getResources(). getColor(R.color.gray_radio));
            //  rb.setHeight(20);
            rg.addView(rb);
            RadioGroup.LayoutParams layoutParams= (RadioGroup.LayoutParams) rb.getLayoutParams();
            layoutParams.topMargin=20;
            layoutParams.width= (int) (ScreenSize.getScreenSize(this).second*0.5f);
            rb.setLayoutParams(layoutParams);


        }
        //change text of textview just before the stats activity
        if (k == questionData.size() - 1) {
            next.setText(R.string.TextStats);
            next.setTextColor(getResources().getColor(R.color.orangeColor));
        }

        next.setOnClickListener(this);
    }

    public void goToNextActivity() {
        Log.d("score befor inte", String.valueOf(score));
        statsIntent.putExtra(SCORE_KEY, score);
        statsIntent.putExtra(QUESTIONS_SIZE_KEY, count_size);

        startActivity(statsIntent);
        finish();


    }

    void CalculateScore(int index) {
        switch (index) {
            case 0:
                break;
            case 1:

                score += 1;
                Log.d("calc score", String.valueOf(score));
                break;


            case 2:
                score += 3;
                Log.d("calc score", String.valueOf(score));
                break;


            case 3:
                score += 5;
                Log.d("calc score", String.valueOf(score));
                break;


            default:
                break;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnNext:
                Log.d("selected id", String.valueOf(getIndexofSelectedRadioButton()));

                CalculateScore(getIndexofSelectedRadioButton());


                int m = ++i;
                if (m==2 || m==5|| m==7)
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());

                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }

                if (m < questionData.size())
                {

                    if(getIndexofSelectedRadioButton()!=-1)
                        getQuestions(m);

                    else
                        noResponseDialog.show();

                }



                else {

                    goToNextActivity();

                }


        }

    }

  /*  @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {



        int m=++i;
        if(m<questionData.size())
            getQuestions(m);
        else
            goToNextActivity();


    }*/
}
