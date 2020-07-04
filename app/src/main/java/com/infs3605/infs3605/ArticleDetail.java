package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ArticleDetail extends AppCompatActivity {
    TextView articleTitle;
    TextView articleDate;
    TextView articleContent;
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        articleTitle = findViewById(R.id.article_detail_title);
        articleDate = findViewById(R.id.article_detail_date);
        articleContent = findViewById(R.id.article_detail_content);

        Intent intent = getIntent();
        article = intent.getParcelableExtra("Article Object");

        articleTitle.setText(article.getTitle());
        articleDate.setText(article.getDate());
        articleContent.setText(article.getContent());

    }
}