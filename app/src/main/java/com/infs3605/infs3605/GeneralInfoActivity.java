package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class GeneralInfoActivity extends AppCompatActivity {
    private Spinner generalInfoSpinner;
    private RecyclerView generalInfoRecyclerView;
    private GeneralInfoActivityAdapter mGeneralInfoActivityAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView blah;


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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<GeneralInfoArticle> generalInfoData = new ArrayList<GeneralInfoArticle>();
        generalInfoSpinner.setAdapter(adapter);

        generalInfoData.add(new GeneralInfoArticle("Tax Support","ATO administrative relief","4/08/2020", "The Australian Taxation Office (ATO) will provide administrative relief for certain tax obligations (similar to relief provided following the bushfires) for taxpayers affected by the coronavirus outbreak"));
        generalInfoData.add(new GeneralInfoArticle("Tax Support","Low Interest Payment Plans","4/08/2020", "If your business has been affected by the coronavirus and you need help to pay your existing and ongoing tax liabilities, you can contact the ATO to discuss entering a low interest payment plan."));
        generalInfoData.add(new GeneralInfoArticle("Tax Support","Payment Deferrals","4/08/2020", "If you have been affected by the coronavirus, the ATO can work with you to defer some payments and vary instalments you have due."));
        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","May 2020 phone scam","15/05/2020", "The ATO is receiving reports of scammers pretending to be from the ATO calling members of the public and asking them to provide their bank account details. They are telling them that their employer has registered them for the JobKeeper Payment, but that the ATO needs their bank account details to deposit the funds into their account."));
        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","Misinformation and Scams","15/05/2020", "Cybercriminals are using websites, emails and text messages that claim to provide official information about the coronavirus, but are attempts to get your personal or business information."));
        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","What can you do to protect your business?","15/05/2020", "Don’t click on any links in emails, text messages or attachments from people or organisations you don’t know."));
        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Closing your Business","27/05/2020", "It can be a difficult decision to close your business, and may take some time to take care of everything. "));
        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Business Registrations","27/05/2020", "If you have temporarily ceased some of your trading activities but intend to restart your business when you can, you’re not required to cancel your ABN and GST registrations. Even if you have paused your business for a lengthy or uncertain period."));
        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Tax and Super obligations","27/05/2020", "If you’re closing your business you will need to continue to meet your tax and super obligations, including:\n "));
        generalInfoData.add(new GeneralInfoArticle("Safe Business Operations","How do I keep my employees safe?","26/05/2020", "To keep workers safe and limit the spread of COVID-19, every employer should do the following at their workplace: "));
        generalInfoData.add(new GeneralInfoArticle("Safe Business Operations","Protect vulnerable people in your workplace","26/05/2020", "If you have a vulnerable person working for you, you should support them to work from home where possible. If that isn’t possible, you must do a risk assessment and may need to make other work arrangements for them.\n. "));

        mGeneralInfoActivityAdapter = new GeneralInfoActivityAdapter(generalInfoData);
        generalInfoRecyclerView.setAdapter(mGeneralInfoActivityAdapter);
    }
//
//    ArrayList<GeneralInfoArticle> generalInfoData = new ArrayList<GeneralInfoArticle>();
//
//    private ArrayList<GeneralInfoArticle> getInfoArticles(){
//
//        generalInfoData.clear();
//
//        generalInfoData.add(new GeneralInfoArticle("Tax Support","ATO administrative relief","4/08/2020", "The Australian Taxation Office (ATO) will provide administrative relief for certain tax obligations (similar to relief provided following the bushfires) for taxpayers affected by the coronavirus outbreak"));
//        generalInfoData.add(new GeneralInfoArticle("Tax Support","Low Interest Payment Plans","4/08/2020", "If your business has been affected by the coronavirus and you need help to pay your existing and ongoing tax liabilities, you can contact the ATO to discuss entering a low interest payment plan."));
//        generalInfoData.add(new GeneralInfoArticle("Tax Support","Payment Deferrals","4/08/2020", "If you have been affected by the coronavirus, the ATO can work with you to defer some payments and vary instalments you have due."));
//        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","May 2020 phone scam","15/05/2020", "The ATO is receiving reports of scammers pretending to be from the ATO calling members of the public and asking them to provide their bank account details. They are telling them that their employer has registered them for the JobKeeper Payment, but that the ATO needs their bank account details to deposit the funds into their account."));
//        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","Misinformation and Scams","15/05/2020", "Cybercriminals are using websites, emails and text messages that claim to provide official information about the coronavirus, but are attempts to get your personal or business information."));
//        generalInfoData.add(new GeneralInfoArticle("COVID 19 scams","What can you do to protect your business?","15/05/2020", "Don’t click on any links in emails, text messages or attachments from people or organisations you don’t know."));
//        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Closing your Business","27/05/2020", "It can be a difficult decision to close your business, and may take some time to take care of everything. "));
//        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Business Registrations","27/05/2020", "If you have temporarily ceased some of your trading activities but intend to restart your business when you can, you’re not required to cancel your ABN and GST registrations. Even if you have paused your business for a lengthy or uncertain period."));
//        generalInfoData.add(new GeneralInfoArticle("Closing or Pausing","Tax and Super obligations","27/05/2020", "If you’re closing your business you will need to continue to meet your tax and super obligations, including:\n "));
//        generalInfoData.add(new GeneralInfoArticle("Safe Business Operations","How do I keep my employees safe?","26/05/2020", "To keep workers safe and limit the spread of COVID-19, every employer should do the following at their workplace: "));
//        generalInfoData.add(new GeneralInfoArticle("Safe Business Operations","Protect vulnerable people in your workplace","26/05/2020", "If you have a vulnerable person working for you, you should support them to work from home where possible. If that isn’t possible, you must do a risk assessment and may need to make other work arrangements for them.\n. "));
//
//        return generalInfoData;
//    }

}