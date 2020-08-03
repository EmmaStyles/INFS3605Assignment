package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class SchemesActivity extends AppCompatActivity implements ArticleClickInterface {
    private RecyclerView schemesRecyclerView;
    private SchemesActivityAdapter mSchemesActivityAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Scheme> schemeData = new ArrayList<Scheme>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);

        this.setTitle("CovidAware");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationPane);

        //checks/selects the bottom icons as they are clicked
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(SchemesActivity.this, GeneralInfoActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.industries:
                        Intent intent2 = new Intent(SchemesActivity.this, MainActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.liveUpdates:
                        Intent intent3 = new Intent(SchemesActivity.this, LiveUpdatesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.schemes:
                        break;
                    case R.id.saved:
                        Intent intent4 = new Intent(SchemesActivity.this, SavedActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;

                }
                return false;
            }
        });
        schemesRecyclerView = (RecyclerView) findViewById(R.id.rv_schemes);
        schemesRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager((getApplicationContext()));
        schemesRecyclerView.setLayoutManager(layoutManager);

//        WebView webview = new WebView(this);
//        setContentView(webview);
//        webview.loadUrl("https://www.business.gov.au/Grants-and-Programs");
//
//        schemeData.add(new Scheme("$3000 grants for small businesses", "16 August 2020", "https://www.service.nsw.gov.au/transaction/apply-small-business-covid-19-recovery-grant"));
//        schemeData.add(new Scheme("Service NSW COVID-19 Assistance Finder", "check Eligibility", "https://www.service.nsw.gov.au/transaction/apply-small-business-covid-19-recovery-grant"));
//        schemeData.add(new Scheme("Assistance for Exporters", "register interest", "https://www.business.nsw.gov.au/export-from-nsw/export-assistance/export-support-packages"));
        readData();
        mSchemesActivityAdapter = new SchemesActivityAdapter(schemeData,this);
        schemesRecyclerView.setAdapter(mSchemesActivityAdapter);
    }

    @Override
    public void onArticleClick(int position) {
        Intent intent = new Intent(this, SchemeDetailActivity.class);
        intent.putExtra("schemeObject",schemeData.get(position));

        startActivity(intent);
    }

    private void readData() {
        InputStream is = getResources().openRawResource(R.raw.schemes_data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line;
        try {

            //step over headers
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                Scheme data = new Scheme();

                if(tokens[0].length()>0){
                    data.setTitle(tokens[0]);
                }
                else{
                    data.setTitle(" ");
                }
                if(tokens[1].length()>0){
                    String contentUnformatted = tokens[1];
                    String contentFormatted = contentUnformatted.replace("-",", ");
                    data.setDateCloses(contentFormatted);
                }
                else{
                    data.setDateCloses(" ");
                }

                if(tokens[2].length()>0){
                    data.setInfoURL(tokens[2]);
                }
                else{
                    data.setInfoURL(" ");
                }

                schemeData.add(data);
                Log.d("SchemesActivity", "Just Created" + data.toString());
            }
        } catch (IOException e) {
            Log.d("SchemesActivity", "Error Reading data file",e);
            e.printStackTrace();
        }
    }
}
