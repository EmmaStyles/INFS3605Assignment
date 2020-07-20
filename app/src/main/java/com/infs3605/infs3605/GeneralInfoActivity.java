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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeneralInfoActivity extends AppCompatActivity implements ArticleClickInterface, AdapterView.OnItemSelectedListener {
    private Spinner generalInfoSpinner;
    private RecyclerView generalInfoRecyclerView;
    private GeneralInfoActivityAdapter mGeneralInfoActivityAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<GeneralInfoArticle> generalInfoData = new ArrayList<GeneralInfoArticle>();
    ArrayList<GeneralInfoArticle> orderedData = new ArrayList<GeneralInfoArticle>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationPane);

        //checks/selects the bottom icons as they are clicked
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        break;
                    case R.id.industries:
                        Intent intent1 = new Intent(GeneralInfoActivity.this, MainActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.liveUpdates:
                        Intent intent2 = new Intent(GeneralInfoActivity.this, LiveUpdatesActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.schemes:
                        Intent intent3 = new Intent(GeneralInfoActivity.this, SchemesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });

        generalInfoSpinner = findViewById(R.id.generalInfo_spinner);
        generalInfoRecyclerView = (RecyclerView) findViewById(R.id.rv_general_info);
        generalInfoRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager((getApplicationContext()));
        generalInfoRecyclerView.setLayoutManager(layoutManager);

        ArrayList<String> spinnerEntries = new ArrayList<>();
        spinnerEntries.add("All");
        spinnerEntries.add("Tax Support");
        spinnerEntries.add("COVID 19 scams");
        spinnerEntries.add("closing or pausing");
        spinnerEntries.add("Safe Business Operations");

        ArrayAdapter<String> genInfoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerEntries);
        genInfoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        generalInfoSpinner.setAdapter(genInfoAdapter);
        generalInfoSpinner.setOnItemSelectedListener(this);


    }

//    private ArrayList<GeneralInfoArticle> getInfoArticles()  {
//        generalInfoData.clear();
//
//        generalInfoData.add(new GeneralInfoArticle("Tax Support","ATO administrative relief","3/08/2020", "The Australian Taxation Office (ATO) will provide administrative relief for certain tax obligations (similar to relief provided following the bushfires) for taxpayers affected by the coronavirus outbreak"));
//        generalInfoData.add(new GeneralInfoArticle("Tax Support","Low Interest Payment Plans","6/08/2020", "If your business has been affected by the coronavirus and you need help to pay your existing and ongoing tax liabilities, you can contact the ATO to discuss entering a low interest payment plan."));
//        generalInfoData.add(new GeneralInfoArticle("Tax Support","Payment Deferrals","4/08/2020", "If you have been affected by the coronavirus, the ATO can work with you to defer some payments and vary instalments you have due."));
//        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","May 2020 phone scam","15/05/2020", "The ATO is receiving reports of scammers pretending to be from the ATO calling members of the public and asking them to provide their bank account details. They are telling them that their employer has registered them for the JobKeeper Payment, but that the ATO needs their bank account details to deposit the funds into their account."));
//        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","Misinformation and Scams","17/05/2020", "Cybercriminals are using websites, emails and text messages that claim to provide official information about the coronavirus, but are attempts to get your personal or business information."));
//        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","What can you do to protect your business?","22/05/2020", "Don’t click on any links in emails, text messages or attachments from people or organisations you don’t know."));
//        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Closing your Business","27/05/2020", "It can be a difficult decision to close your business, and may take some time to take care of everything. "));
//        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Business Registrations","29/05/2020", "If you have temporarily ceased some of your trading activities but intend to restart your business when you can, you’re not required to cancel your ABN and GST registrations. Even if you have paused your business for a lengthy or uncertain period."));
//        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Tax and Super obligations","21/05/2020", "If you’re closing your business you will need to continue to meet your tax and super obligations, including:\n "));
//        generalInfoData.add(new GeneralInfoArticle("Safe Business Operations","How do I keep my employees safe?","26/05/2020", "To keep workers safe and limit the spread of COVID-19, every employer should do the following at their workplace: "));
//        generalInfoData.add(new GeneralInfoArticle("Safe Business Operations","Protect vulnerable people in your workplace","23/05/2020", "If you have a vulnerable person working for you, you should support them to work from home where possible. If that isn’t possible, you must do a risk assessment and may need to make other work arrangements for them.\n. "));
//
//
//        return generalInfoData;
//    }



    @Override
    public void onArticleClick(int position) {
        Intent intent = new Intent(this,GeneralInfoDetailActivity.class);
        intent.putExtra("GIobject",orderedData.get(position));

        startActivity(intent);

    }

    ArrayList<GeneralInfoArticle> infoArticlesToDisplay = new ArrayList<GeneralInfoArticle>();

    private void getSelectedArticleType(String articleTypeChosen) {
//        getInfoArticles();
        readData();
        ArrayList<GeneralInfoArticle> reOrderedList = new ArrayList<GeneralInfoArticle>();
        if (articleTypeChosen.equalsIgnoreCase("all")) {
            Collections.sort(generalInfoData, new SortByDate());
            reOrder(generalInfoData);

            mGeneralInfoActivityAdapter = new GeneralInfoActivityAdapter(orderedData, this);

        } else {
            infoArticlesToDisplay.clear();
//
            for( int i = 0; i<generalInfoData.size(); i++){
                if(generalInfoData.get(i).getArticleType().equalsIgnoreCase(articleTypeChosen)){
                    infoArticlesToDisplay.add(new GeneralInfoArticle(generalInfoData.get(i).getArticleType(), generalInfoData.get(i).generalInfoTitle, generalInfoData.get(i).generalInfoDate, generalInfoData.get(i).generalInfoContent));
                }
            }
            Collections.sort(infoArticlesToDisplay, new SortByDate());
            reOrder(infoArticlesToDisplay);
            mGeneralInfoActivityAdapter = new GeneralInfoActivityAdapter(orderedData, this);
        }
        generalInfoRecyclerView.setAdapter(mGeneralInfoActivityAdapter);

    }

    String GenInfoArticleType;
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        GenInfoArticleType = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), GenInfoArticleType, Toast.LENGTH_SHORT ).show();

        getSelectedArticleType(GenInfoArticleType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private ArrayList<GeneralInfoArticle> reOrder(ArrayList<GeneralInfoArticle> data){
       orderedData.clear();
        this.orderedData = data;
        Collections.reverse(data);
        return data;
    }

    //

    //reads the general_info CSV file from the /raw folder.
    private void readData() {
        //access the resources directory
        InputStream inputStream = getResources().openRawResource(R.raw.general_info);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );

        String line = "";
        //if the line is not null keep looping
        try {
            //skip the header
            reader.readLine();

            while( (line = reader.readLine()) != null){
                //split by ','s
                String[] tokens = line.split(",");

                //read the data
                String type = tokens[0];
                String title = tokens[1];
                String date = tokens[2];
                String contentUnformatted = tokens[3];
                String contentFormatted = contentUnformatted.replace("-",", ");

                GeneralInfoArticle generalInfoArticle = new GeneralInfoArticle(type, title, date, contentFormatted);
                //which arraylist do you want to add it to?

                generalInfoData.add(generalInfoArticle);

                //debug statement
                Log.d("MainActivity", "Just created " + generalInfoArticle.toString());
            }
        }catch(IOException e){
            Log.wtf("MainActivity", "Error reading data file " + line, e);
            e.printStackTrace();
        }

    }

}