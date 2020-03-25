package com.training.coronavirus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class QuestionActivity extends BaseActivity implements View.OnClickListener {

    private TextView question, questionNo;
    private RadioGroup rg;
    private TextView next;
    private Intent statsIntent;
    static final String SCORE_KEY = "SCORE_KEY";
    static final String QUESTIONS_SIZE_KEY = "QUESTIONS_SIZE_KEY";
private ImageView questionImage;
private LinkedHashMap<Integer,String> questions=new LinkedHashMap<>();
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


questions.put(R.mipmap.fever,"هل لديك حمى  ؟");
questions.put(R.mipmap.cough_fever,"هل تسعل بشدة ولفترات متكررة في اليوم الواحد ؟");
questions.put(R.mipmap.diziness,"هل تشتكي من أوجاع في عدة أماكن في جسمك؟");

        questions.put(R.mipmap.throat_pain,"هل تشعر بالتهاب في الحلق وحرقة عند مستوى الحنجرة؟");
        questions.put(R.mipmap.fatigue,"هل تشعر بتعب و ارهاق شديدين يأثران على القيام بشؤونك اليومية؟");
        questions.put(R.mipmap.runny_nose,"هل لديك سيلان متواصل للأنف ؟");
        questions.put(R.mipmap.breathing_difficuly,"هل تعاني من صعوبة في التنفس ؟");

        questionImage=findViewById(R.id.question_image);
        question = findViewById(R.id.textQuestion);
        rg = findViewById(R.id.radiogroupChoices);
        questionNo = findViewById(R.id.textQNo);
        next = findViewById(R.id.btnNext);
        next.setOnClickListener(this);

        ConstraintLayout.LayoutParams layoutParams= (ConstraintLayout.LayoutParams) questionImage.getLayoutParams();
        layoutParams.width= (int) (ScreenSize.getScreenSize(this).first*0.2f);
        layoutParams.height= (int) (ScreenSize.getScreenSize(this).second*0.22f);

        questionImage.setLayoutParams(layoutParams);


        noResponseDialog=new AlertDialog.Builder(this).setPositiveButton("حسنا", dialogListener
        ).setMessage("يجب عليك اختيار اجابة").create();

        k = 0;

        statsIntent = new Intent(QuestionActivity.this, StatsActivity.class);
        statsIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (getIntent().getExtras() != null) {


            count_size = questions.size();


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

        question.setText(new ArrayList<>(questions.values()).get(k));
       questionImage.setImageDrawable(getResources().getDrawable(new ArrayList<>(questions.keySet()).get(k)));

int questionCounter=k+1;
        questionNo.setText("السؤال " +questionCounter + " من "+questions.size()+" أسئلةً");




                // new URLImage(this).execute(questionData.get(k).getImageURL());
        //set image with name
        // int id = getResources().getIdentifier(questionData.get(k).getImageURL(), "drawable", QuestionActivity.this.getPackageName());

        //   questionImage.setImageResource(id);


        // answer = questionData.get(k).getAnswer();
        for (int j = 0; j < 4; j++) {
            RadioButton rb = new RadioButton(this);
            rb.setButtonDrawable(getResources().getDrawable(R.drawable.null_selector));
            rb.setBackground(getResources().getDrawable(R.drawable.my_selector));
            radioId = Integer.parseInt("1000" + (j + 1) + k);
            rb.setId(radioId);
            switch (j)
            {
                case 0: rb.setText("لا");
                break;
                case 1: rb.setText("ممكن");
                    break;
                case 2: rb.setText("قليلا");
                    break;
                case 3: rb.setText("متأكد");
                    break;

            }


            rb.setTextSize(30);
rb.setTextColor(getResources(). getColor(R.color.gray_radio));
            //  rb.setHeight(20);
            rg.addView(rb);
            RadioGroup.LayoutParams layoutParams= (RadioGroup.LayoutParams) rb.getLayoutParams();
            layoutParams.topMargin=20;
            rb.setPadding(0,0,20,0);

            layoutParams.width= (int) (ScreenSize.getScreenSize(this).second*0.5f);
            rb.setLayoutParams(layoutParams);


        }
        //change text of textview just before the stats activity
        if (k ==count_size - 1) {
            next.setText(R.string.TextStats);
            next.setTextColor(getResources().getColor(R.color.yellow));
            next.setTextSize(32);
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
                if (m==2 || m==4 || m==5|| m==6)
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());

                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }

                if (m < count_size/*-1*/)
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
