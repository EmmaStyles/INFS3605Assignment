package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


//when an industry is clicked on the main activity, it opens up this activity
public class IndustryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ArticleClickInterface {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private IndustryActivityAdapter mIndustryActAdapter;
    private RecyclerView.LayoutManager layoutManager;


IndustryClass industryClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationPane);

        //checks/selects the bottom icons as they are clicked
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(IndustryActivity.this, MainActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.industries:
                        break;
                    case R.id.liveUpdates:
                        Intent intent2 = new Intent(IndustryActivity.this, LiveUpdatesActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.schemes:
                        Intent intent3 = new Intent(IndustryActivity.this, SchemesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });

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

        recyclerView.setLayoutManager(layoutManager);

    }
    ArrayList<Article> data = new ArrayList<Article>();

    private ArrayList<Article> getArticles(){

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
    ArrayList<Article> articlesToDisplay = new ArrayList<Article>();
    private void getSelectedSegment(String segment){

        getArticles();
        String industryChosen = industryClass.getIndustryName();
        if(segment.equalsIgnoreCase("All")) {

            for(int i = 0; i< data.size(); i++){
                if(data.get(i).getIndustry().equalsIgnoreCase(industryChosen)){
                    articlesToDisplay.add(new Article(data.get(i).getIndustry(), data.get(i).getSegment() ,data.get(i).getTitle(), data.get(i).getDate(), data.get(i).getContent()));
                }else{

                }

            }
            mIndustryActAdapter = new IndustryActivityAdapter(articlesToDisplay, this);


        } else{
            articlesToDisplay.clear();
            for(Article article : getArticles()){
                if(article.getSegment().equalsIgnoreCase(segmentChosen)){
                    articlesToDisplay.add(article);
                }
            }
            mIndustryActAdapter = new IndustryActivityAdapter(articlesToDisplay, this);
        }
        recyclerView.setAdapter(mIndustryActAdapter);

    }

String segmentChosen;
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
         segmentChosen = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), segmentChosen, Toast.LENGTH_SHORT ).show();

        getSelectedSegment(segmentChosen);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onArticleClick(int position) {
        Intent intent = new Intent(this,ArticleDetail.class);
        intent.putExtra("Article Object", articlesToDisplay.get(position));

        startActivity(intent);

        //the only thing missing for this intent to work, is passing the Article object, referring to main Activity
        // we should do putExtra(" ", articlesToDisplay.get(position)) but since our articleToDisplay is not a class variable we cannot do it here

    }
}
