package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class SchemesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);

        WebView webview = new WebView(this);
        setContentView(webview);
        webview.loadUrl("https://www.business.gov.au/Grants-and-Programs");

    }
}
