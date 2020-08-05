package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class GeneralInfoDetailActivity extends AppCompatActivity {
    TextView genInfo_title;
    TextView genInfo_date;
    TextView genInfo_content;
    GeneralInfoArticle generalInfoArticle;
    ArrayList<String> articleSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info_detail);

        this.setTitle("CovidAware");

        Intent intent = getIntent();
        generalInfoArticle = intent.getParcelableExtra("GIobject");

        genInfo_title = findViewById(R.id.GI_article_title);
        genInfo_date = findViewById(R.id.GI_article_date);
        genInfo_content = findViewById(R.id.GI_article_content);



        articleSent = intent.getStringArrayListExtra("article");

        if(generalInfoArticle != null) {
            genInfo_title.setText(generalInfoArticle.getGeneralInfoTitle());
            genInfo_date.setText(generalInfoArticle.getGeneralInfoDate());
            genInfo_content.setText(generalInfoArticle.getGeneralInfoContent());
        }else{
            genInfo_title.setText(articleSent.get(0));
            genInfo_date.setText(articleSent.get(1));
            genInfo_content.setText(articleSent.get(2));

        }


    }
}