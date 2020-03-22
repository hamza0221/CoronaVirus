package com.training.coronavirus;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements  CoronaAdapter.ItemClickListener {

    ArrayList<Question> questionsData = new ArrayList<Question>();
    static final String QUESTIONS_KEY = "QUESTIONS";

    RecyclerView recyclerView;
    private CoronaAdapter coronaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        List<String> coronaTextList=new ArrayList<>();
        List<Integer> coronaImages=new ArrayList<>();

        coronaImages.add(R.mipmap.corona);
        coronaImages.add(R.mipmap.corona_map);
        coronaImages.add(R.mipmap.corona_info);
        coronaImages.add(R.mipmap.corona_prevention);



        coronaTextList.add("إختبار : هل أنت مصاب بمرض الكورونا");
        coronaTextList.add("إحصائيات كورونا حول العالم");
        coronaTextList.add("معلومات حول الكورونا");
        coronaTextList.add("كيفية الوقاية من الكورونا");
        coronaAdapter=new CoronaAdapter();
        coronaAdapter.setCoronaItems(coronaTextList);
        coronaAdapter.setCoronaImages(coronaImages);
      coronaAdapter.setItemClickListener(this);
        recyclerView.setAdapter(coronaAdapter);







        setupAdAtBottom();






    }

    @Override
    public void onItemClickListener(int position) {
        switch (position)
        { case 0:      Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            intent.putParcelableArrayListExtra(QUESTIONS_KEY, questionsData);
            startActivity(intent);


            break;

            case 1:startActivity(new Intent(this,StatisticesActivity.class));
             break;

            case 2:startActivity(new Intent(this,CoronaInfo.class));
                break;
            case 3:startActivity(new Intent(this,PreventionActivity.class));
                break;


        }
    }
}
