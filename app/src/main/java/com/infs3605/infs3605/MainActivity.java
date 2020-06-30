package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

//hello im commenting in the main activity
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<IndustryClass> myDataset = new ArrayList<IndustryClass>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.maRecycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager  = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        myDataset.add(new IndustryClass("Health"));
        myDataset.add(new IndustryClass("Fitness"));
        myDataset.add(new IndustryClass("Food"));
        myDataset.add(new IndustryClass("Retail"));


        mAdapter = new MainActivityAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);



    }
}
