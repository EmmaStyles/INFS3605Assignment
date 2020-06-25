package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//when an industry is clicked on the main activity, it opens up this activity
public class IndustryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);
    }
}
