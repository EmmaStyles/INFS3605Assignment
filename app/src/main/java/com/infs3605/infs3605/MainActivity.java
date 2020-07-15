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


        myDataset.add(new IndustryClass("Travel", travelSegements,"https://www.executivetraveller.com/photos/view/size:1200,675/5f03fb22fc484042a2d70e33dd799465-au-nz-travel-bubble-2000a.jpg"));
        myDataset.add(new IndustryClass("Property", propertySegments,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSM_h9RM-rrmUb4lTOk2-uwke10sJQJOfWxNg&usqp=CAU"));
        myDataset.add(new IndustryClass("Beauty", beautySegments,"https://specials-images.forbesimg.com/imageserve/5ec2b5250f6b6d0007bcf49a/960x0.jpg?fit=scale"));
        myDataset.add(new IndustryClass("Community", communitySegments,"https://media.apnarm.net.au/media/images/2020/06/20/v3imagesbin2a7a01652ab90046fe1a851d8a36590d-ni10d1dr2uvjjp0fju2.jpg"));
        myDataset.add(new IndustryClass("Entertainment", entertainmentSegments,"https://sydneylivingmuseums.com.au/sites/default/files/MOS-gallery-8.jpg"));
        myDataset.add(new IndustryClass("Health", healthSegments,"https://www.stuartmagazine.com/sites/default/files/features/intro_2.jpg"));
        myDataset.add(new IndustryClass("Sport", sportSegments, "https://static.ffx.io/images/$zoom_0.133%2C$multiply_0.5896%2C$ratio_1.7777777777777777%2C$width_636%2C$x_0%2C$y_66/t_crop_custom/q_86%2Cf_auto/90a6d8b006a5f4389352a5fc78639c6c0b3d82d7"));
        myDataset.add(new IndustryClass("Retail", retailSegments,"https://lh3.googleusercontent.com/proxy/DGW-dSgiUhpJbWYZ5RHQr3BWiQ6oz5zwoGjj2jGlytrJpD25J2PkCVLEjhON0S3hAyrHCRRFL3EpK1JBDgmC7ur1oSp9fxMYfiL7E1cd_wa-xS8Dg5maNztmoR8J15SYwEInQ1taOd49oMcPFqk1IC2JeHjx_4vRGbtKEmzCof3qOpYZDiVBJUDJvR5RcoGlJpfaM_5knyVebHlxbMabFOxpRQ-Hb8qJhDgIz_c"));
        myDataset.add(new IndustryClass("Hospitality",hospitalitySegments,"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTLs511TuuSi2svt4heZsMPLhgb6o1H0rE9xQ&usqp=CAU"));



        mAdapter = new MainActivityAdapter(myDataset,this, this);
        recyclerView.setAdapter(mAdapter);

    }

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
