package com.infs3605.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


//when an industry is clicked on the main activity, it opens up this activity
public class IndustryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private IndustryActivityAdapter mIndustryActAdapter;
    private RecyclerView.LayoutManager layoutManager;
//    ArrayList<Article> myDataset = new ArrayList<Article>();
//    ArrayList<Article> getMyDatasetFiltered = new ArrayList<Article>(); //commented for camposha filter
IndustryClass industryClass;
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
         industryClass = intent.getParcelableExtra("Industry Object");

        ArrayList<String> industrySegments= new ArrayList<>();
        industrySegments.add("All");

            //Fills the ArrayList industrySegments with segments that come from the parcelable Industry Object clicked on
        for(int i=0; i<industryClass.industrySegments.size(); i++){
            String segment = industryClass.industrySegments.get(i);
            industrySegments.add(segment);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,industrySegments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

//        myDataset.add(new Article("Travel", "Hotels", "Accommodation, Holiday Homes & Holiday Rentals", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
//        myDataset.add(new Article("Property", "Real Estate", "Auction houses (other than Clearing houses)", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
//        myDataset.add(new Article("Hospitality", "Bars", "Bars, pubs, clubs, cellar doors, micro-breweries, distilleries and casinos", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres (excluding staff) per existing separate seated food or drink area.\n"));
//        myDataset.add(new Article("Beauty", "Nail Salons", "Beauty, nail, tanning and waxing salons, tattoo and massage parlours and spas", "23/06/2020", "Capacity must not exceed 20 customers or one customer per 4 square metres.\n"));
//        myDataset.add(new Article("Hospitality", "Restaurants", "Cafes and restaurants", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres.\n"));

        // commented only for camposha tutorial addition, without it this sld be uncommented
//        mIndustryActAdapter = new IndustryActivityAdapter(getMyDatasetFiltered);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(mIndustryActAdapter); commented for camposha tutorial
    }
    ArrayList<Article> data = new ArrayList<Article>();
    //camposha tutorial
    private ArrayList<Article> getArticles(){
//        ArrayList<Article> data = new ArrayList<Article>();
        data.clear();

        data.add(new Article("Travel", "Hotels", "Accommodation, Holiday HomeS & Holiday Rentals", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
        data.add(new Article("Property", "Real Estate", "Auction houses (other than Clearing houses)", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
        data.add(new Article("Hospitality", "Bars", "Bars, pubs, clubs, cellar doors, micro-breweries, distilleries and casinos", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres (excluding staff) per existing separate seated food or drink area.\n"));
        data.add(new Article("Beauty", "Nail Salons", "Beauty, nail, tanning and waxing salons, tattoo and massage parlours and spas", "23/06/2020", "Capacity must not exceed 20 customers or one customer per 4 square metres.\n"));
        data.add(new Article("Hospitality", "Restaurants and Cafes", "Cafes and restaurants", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres.\n"));
        data.add(new Article("Hospitality", "Restaurants and Cafes", "Food Courts", "23/06/2020", "Operators must have a COVID safety plan\n"));
        data.add(new Article("Beauty", "Hairdressers", "Hairdresses and barbers", "23/06/2020", "Hairdressing salons and barbers may open with restrictions.\n"));

        return data;
    }

    private void getSelectedSegment(String segment){
        ArrayList<Article> articlesToDisplay = new ArrayList<Article>();
        getArticles();
        String industryChosen = industryClass.getIndustryName();
        if(segment.equalsIgnoreCase("All")){

            for(int i = 0; i< data.size(); i++){
                if(data.get(i).getIndustry().equalsIgnoreCase(industryChosen)){
                    articlesToDisplay.add(new Article(data.get(i).getIndustry(), data.get(i).getSegment() ,data.get(i).getTitle(), data.get(i).getDate(), data.get(i).getContent()));
                }else{
//                    data.remove(i);
                }

            }
            mIndustryActAdapter = new IndustryActivityAdapter(articlesToDisplay);
//            mIndustryActAdapter = new IndustryActivityAdapter(getArticles());

        } else{
            articlesToDisplay.clear();
            for(Article article : getArticles()){
                if(article.getSegment().equalsIgnoreCase(segmentChosen)){
                    articlesToDisplay.add(article);
                }
            }
            mIndustryActAdapter = new IndustryActivityAdapter(articlesToDisplay);
        }
        recyclerView.setAdapter(mIndustryActAdapter);

    }
    //camposha tutorial addition end
String segmentChosen;
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
         segmentChosen = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), segmentChosen, Toast.LENGTH_SHORT ).show();

        getSelectedSegment(segmentChosen); //added for camposha tutorial

        //commented out for the camposha tutorial
//        for(int i=0; i<myDataset.size(); i++){
//            if (myDataset.get(i).segment.equalsIgnoreCase(segmentChosen)){
//                getMyDatasetFiltered.add(new Article(myDataset.get(i).industry, myDataset.get(i).segment, myDataset.get(i).title, myDataset.get(i).date, myDataset.get(i).content));
//            }
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
