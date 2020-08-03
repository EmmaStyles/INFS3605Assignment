package com.infs3605.infs3605;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

//hello im commenting in the main activity
public class MainActivity extends AppCompatActivity implements IndustryClickInterface {
    private RecyclerView recyclerView;
    private MainActivityAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<IndustryClass> myDataset = new ArrayList<IndustryClass>();

    ArrayList<Article> downloadedData = new ArrayList<Article>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("CovidAware");
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
                        Intent intent1 = new Intent(MainActivity.this, GeneralInfoActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.industries:
                        break;
                    case R.id.liveUpdates:
                        Intent intent2 = new Intent(MainActivity.this, LiveUpdatesActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.schemes:
                        Intent intent3 = new Intent(MainActivity.this, SchemesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.saved:
                        Intent intent5 = new Intent(MainActivity.this, SavedActivity.class);
                        startActivity(intent5);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });



        recyclerView = (RecyclerView) findViewById(R.id.maRecycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager  = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList <String> travelSegements = new ArrayList<String>();
        travelSegements.add("Hotels");
        travelSegements.add("Airlines");
        ArrayList <String> propertySegments = new ArrayList<String>();
        propertySegments.add("Real estate");
        ArrayList <String> hospitalitySegments = new ArrayList<String>();
        hospitalitySegments.add("Bars");
        hospitalitySegments.add("Restaurants and Cafes");
        ArrayList <String> beautySegments = new ArrayList<String>();
        beautySegments.add("Hairdressers");
        beautySegments.add("Nail Salons");
        ArrayList <String> communitySegments = new ArrayList<String>();
        communitySegments.add("Libraries");
        communitySegments.add("Childcare services");
        communitySegments.add("Community venues");
        ArrayList <String> entertainmentSegments = new ArrayList<String>();
        entertainmentSegments.add("Museums");
        entertainmentSegments.add("Zoos");
        ArrayList <String> healthSegments = new ArrayList<String>();
        healthSegments.add("Healthcare");
        healthSegments.add("Pharmarcy");
        ArrayList <String> sportSegments = new ArrayList<String>();
        sportSegments.add("Gyms");
        sportSegments.add("Water Sports");
        sportSegments.add("Team Sports");
        ArrayList <String> retailSegments = new ArrayList<String>();
        retailSegments.add("Shops");


        myDataset.add(new IndustryClass("Travel", travelSegements,R.drawable.ic_travel));
        myDataset.add(new IndustryClass("Property", propertySegments,R.drawable.ic_property));
        myDataset.add(new IndustryClass("Beauty", beautySegments,R.drawable.ic_beauty));
        myDataset.add(new IndustryClass("Community", communitySegments,R.drawable.ic_community));
        myDataset.add(new IndustryClass("Entertainment", entertainmentSegments,R.drawable.ic_entertainment));
        myDataset.add(new IndustryClass("Health", healthSegments,R.drawable.ic_health));
        myDataset.add(new IndustryClass("Sport", sportSegments, R.drawable.ic_sport));
        myDataset.add(new IndustryClass("Retail", retailSegments,R.drawable.ic_retail));
        myDataset.add(new IndustryClass("Hospitality",hospitalitySegments,R.drawable.ic_hospitality));



        mAdapter = new MainActivityAdapter(myDataset,this, this);

        recyclerView.setAdapter(mAdapter);



        Log.d("main activity", "data should still have " + downloadedData.size());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_function, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Search Industry");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // not doing anything here since we want the list to be filtered in real time
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }


    @Override
    public void onIndustryClick(int position) {
        Intent intent = new Intent(this, IndustryActivity.class);
        intent.putExtra("Industry Object", myDataset.get(position));
        intent.putParcelableArrayListExtra("Data", downloadedData);


        startActivity(intent);

    }






}
