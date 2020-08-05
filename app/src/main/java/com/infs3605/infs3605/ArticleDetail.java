package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.log4j.chainsaw.Main;

import java.util.ArrayList;

public class ArticleDetail extends AppCompatActivity {
    TextView articleTitle;
    TextView articleDate;
    TextView articleContent;
    Article article;
    ImageView articleImage;
    ArrayList<String> articleSent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        articleTitle = findViewById(R.id.article_detail_title);
        articleDate = findViewById(R.id.article_detail_date);
        articleContent = findViewById(R.id.article_detail_content);
//        articleImage = findViewById(R.id.article_detail_image);

        this.setTitle("CovidAware");

        Intent intent = getIntent();
        article = intent.getParcelableExtra("Article Object");
        articleSent = intent.getStringArrayListExtra("article");

        if(article != null) {
            articleTitle.setText(article.getTitle());
            articleDate.setText(article.getDate());
            articleContent.setText(article.getContent());
//        Picasso.with(this).load(article.getArticleImageUrl()).into(articleImage);
        } else{

            articleTitle.setText(articleSent.get(0));
            articleDate.setText(articleSent.get(1));
            articleContent.setText(articleSent.get(2));
        }

//
    }

}