package com.training.coronavirus;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.training.coronavirus.BaseActivity;
import com.training.coronavirus.DBHandler;
import com.training.coronavirus.Question;
import com.training.coronavirus.R;

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





        //binding
      /*  butt_Quiz = (Button) findViewById(R.id.IdButton_quiz);
        Butt_protection = (Button) findViewById(R.id.IdButton_protection);
        Butt_Statistics = (Button) findViewById(R.id.IdButton_Statistics);
        Infos_Corona = (Button) findViewById(R.id.IdButton_Infos);
        butt_Quiz.setOnClickListener(this);
        Butt_protection.setOnClickListener(this);
        Butt_Statistics.setOnClickListener(this);
        Infos_Corona.setOnClickListener(this);
*/


        DBHandler db = new DBHandler(this);
        //Adding all Questions
        Log.d("Insert: ", "Inserting questions..");
        db.addQuestion(new Question(0, "هل تشعر بإرتفاع في درجة الحرارة الشديدة ؟", String.valueOf(new Gson().toJson(Arrays.asList("لا ", "ممكن", "قليلاً", "نعم أنا متأكدا ")))));
        db.addQuestion(new Question(1, "هل تشعر بسعال جاف يصاحبه سخونة شديدة ؟", String.valueOf(new Gson().toJson(Arrays.asList("لا", "ممكن", "قليلاً", "نعم أنا متأكدا")))));
        db.addQuestion(new Question(2, "هل لديك سيلان في الأنف ؟", String.valueOf(new Gson().toJson(Arrays.asList("لا", "ممكن", "قليلاً", "نعم أنا متأكدا")))));
        db.addQuestion(new Question(3, "هل لديك التهاب في الحلق ؟", String.valueOf(new Gson().toJson(Arrays.asList("لا", "ممكن", "قليلاً", "نعم أنا متأكدا")))));
        db.addQuestion(new Question(4, "هل تشعر بهمدان شديد وعدم القدرة على بذل أي مجهود ؟", String.valueOf(new Gson().toJson(Arrays.asList("لا", "ممكن", "قليلاً", "نعم أنا متأكدا")))));
        db.addQuestion(new Question(5, "هل تشعر بفقدان للشهية مع صعوبة في البلع ؟", String.valueOf(new Gson().toJson(Arrays.asList("لا", "ممكن", "قليلاً", "نعم أنا متأكدا")))));
        db.addQuestion(new Question(6, "هل تشعر باضطراب في درجة الوعي ؟", String.valueOf(new Gson().toJson(Arrays.asList("لا", "ممكن", "قليلاً", "نعم أنا متأكدا")))));
        db.addQuestion(new Question(7, "هل تشعر بإلتهاب في الرئة ؟ ", String.valueOf(new Gson().toJson(Arrays.asList("لا", "ممكن", "قليلاً", "نعم أنا متأكدا")))));

        Log.d("finish: ", "Questions inserted..");

        //reading all the Questions
        Log.d("Reading: ", "Reading all questions..");
        questionsData = db.getAllQuestions();


        for (Question question : questionsData) {
            // Writing shops  to log
            Log.d("Question: : ", question.toString());
        }
        //finish reading all the Questions
        Log.d("Reading:  ", "finish getting question List..");

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

        }
    }
}
