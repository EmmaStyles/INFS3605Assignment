package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


//when an industry is clicked on the main activity, it opens up this activity
public class IndustryActivity extends AppCompatActivity {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private IndustryActivityAdapter mIndustryActAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Article> myDataset = new ArrayList<Article>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);

        spinner = findViewById(R.id.industrySegment_spinner);
        recyclerView = (RecyclerView) findViewById(R.id.rv_industry_activity);
        recyclerView.setHasFixedSize(true);

        layoutManager  = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        IndustryClass industryClass = intent.getParcelableExtra("Industry Object");

        ArrayList<String> industrySegments= new ArrayList<>();

            //Fills the ArrayList industrySegments with segments that come from the parcelable Industry Object clicked on
        for(int i=0; i<industryClass.industrySegments.size(); i++){
            String segment = industryClass.industrySegments.get(i);
            industrySegments.add(segment);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,industrySegments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        myDataset.add(new Article("Travel", "Hotels", "Accommodation, Holiday HomeS & Holiday Rentals", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
        myDataset.add(new Article("Property", "Real Estate", "Auction houses (other than Clearing houses)", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
        myDataset.add(new Article("Hospitality", "Bars", "Accommodation, Holiday Homes & Holiday Rentals", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres (excluding staff) per existing separate seated food or drink area.\n"));
        myDataset.add(new Article("Beauty", "Nail Salons", "Beauty, nail, tanning and waxing salons, tattoo and massage parlours and spas", "23/06/2020", "Capacity must not exceed 20 customers or one customer per 4 square metres.\n"));
        myDataset.add(new Article("Hospitality", "Restaurants", "Cafes and restaurants", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres.\n"));

        mIndustryActAdapter = new IndustryActivityAdapter(myDataset);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mIndustryActAdapter);
    }
}
