package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class SchemeDetailActivity extends AppCompatActivity {
    Scheme schemeItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_detail);

        Intent intent = getIntent();
        schemeItem = intent.getParcelableExtra("schemeObject");


        WebView webview = new WebView(this);
        setContentView(webview);
        webview.loadUrl(schemeItem.getInfoURL());


    }
}