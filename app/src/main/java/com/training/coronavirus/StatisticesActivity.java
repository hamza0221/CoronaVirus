package com.training.coronavirus;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StatisticesActivity extends AppCompatActivity {

    private String cases,deaths,recovered;
    private TextView coronaCases,coronaDeaths,coronaRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_activity);
        GetHtml getHtml=new GetHtml();
        getHtml.execute("https://www.worldometers.info/coronavirus/");


    }


private class GetHtml extends AsyncTask<String,String,Document>{


    @Override
    protected Document doInBackground(String... strings) {
        Document doc = null;
        try {
        doc = Jsoup.connect(strings[0]).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }


    @Override
    protected void onPostExecute(Document document) {
        super.onPostExecute(document);
        setContentView(R.layout.activity_statistices);
        coronaCases=findViewById(R.id.corona_cases);
        coronaDeaths=findViewById(R.id.corona_deaths);
        coronaRecovered=findViewById(R.id.corona_recovered);

        for (int i = 0; i <document.getElementsByClass("maincounter-number").size();i++)
        {
            switch (i)
            {
                case 0:
                    if(document.getElementsByClass("maincounter-number").get(i).childNodes().size()>=2)
                        if(document.getElementsByClass("maincounter-number").get(i).childNode(1).childNodes().size()>=1)
                    cases = document.getElementsByClass("maincounter-number").get(i).childNode(1).childNodes().get(0).toString();

                 coronaCases.setText(cases);
                   break;


                case 1:
                    if(document.getElementsByClass("maincounter-number").get(i).childNodes().size()>=2)
                        if(document.getElementsByClass("maincounter-number").get(i).childNode(1).childNodes().size()>=1)
                    deaths = document.getElementsByClass("maincounter-number").get(i).childNode(1).childNodes().get(0).toString();
                    coronaDeaths.setText(deaths);
                    break;

                case 2:
                    if(document.getElementsByClass("maincounter-number").get(i).childNodes().size()>=2)
                        if(document.getElementsByClass("maincounter-number").get(i).childNode(1).childNodes().size()>=1)
                    recovered = document.getElementsByClass("maincounter-number").get(i).childNode(1).childNodes().get(0).toString();
                  coronaRecovered.setText(recovered);
                    break;








            }







        }
    }
}

}
