package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

//hello im commenting in the main activity
public class MainActivity extends AppCompatActivity implements IndustryClickInterface {
    private RecyclerView recyclerView;
    private MainActivityAdapter mAdapter;
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
        communitySegments.add("Community centres and Halls");
        ArrayList <String> entertainmentSegments = new ArrayList<String>();
        entertainmentSegments.add("Museums");
        entertainmentSegments.add("Zoos");
        ArrayList <String> healthSegments = new ArrayList<String>();
        healthSegments.add("Healthcare");
        healthSegments.add("Pharmarcy");
        ArrayList <String> sportSegments = new ArrayList<String>();
        sportSegments.add("Gyms");
        sportSegments.add("Water Sports");
        ArrayList <String> retailSegments = new ArrayList<String>();
        retailSegments.add("Shops");


        myDataset.add(new IndustryClass("Travel", travelSegements));
        myDataset.add(new IndustryClass("Property", propertySegments));
        myDataset.add(new IndustryClass("Hospitality", hospitalitySegments));
        myDataset.add(new IndustryClass("Beauty", beautySegments));
        myDataset.add(new IndustryClass("Community", communitySegments));
        myDataset.add(new IndustryClass("Entertainment", entertainmentSegments));
        myDataset.add(new IndustryClass("Health", healthSegments));
        myDataset.add(new IndustryClass("Sport", sportSegments));
        myDataset.add(new IndustryClass("Retail", retailSegments));




        mAdapter = new MainActivityAdapter(myDataset,this);
        recyclerView.setAdapter(mAdapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationPane);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(MainActivity.this, GeneralInfoActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.industries:
                        Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.liveUpdates:
                        break;

                    case R.id.schemes:
                        break;
                }

                return false;
            }
        });
//        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
//
//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                   switch (menuItem.getItemId()){
//                       case R.id.home
//                   }
//
//                    return false;
//                }
//            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_function, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

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

        startActivity(intent);

    }
}
