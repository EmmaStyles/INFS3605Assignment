package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//this is the home page, the first activity that loads when users open the app

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

