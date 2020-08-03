package com.infs3605.infs3605;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.infs3605.infs3605.APIEntities.Record;
import com.infs3605.infs3605.APIEntities.RecordsResponse;
import com.infs3605.infs3605.APIEntities.Result;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveUpdatesActivity extends AppCompatActivity {
    private TextView overallCases;
    private TextView newCases;
    private TextView updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_updates);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationPane);
        overallCases = findViewById(R.id.overallCases);
        newCases = findViewById(R.id.newCases);
        updated = findViewById(R.id.updated);
        new GetCovidTask().execute();

        //checks/selects the bottom icons as they are clicked
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        this.setTitle("CovidAware");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(LiveUpdatesActivity.this, GeneralInfoActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.industries:
                        Intent intent2 = new Intent(LiveUpdatesActivity.this, MainActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.liveUpdates:
                        break;
                    case R.id.schemes:
                        Intent intent3 = new Intent(LiveUpdatesActivity.this, SchemesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.saved:
                        Intent intent4 = new Intent(LiveUpdatesActivity.this, SavedActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });

    }

    private class GetCovidTask extends AsyncTask<Void, Void, List<Record>> {

        @Override
        protected List<Record> doInBackground(Void... voids) {
            try{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://data.nsw.gov.au/data/api/3/action/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                CovidAPI covidAPI = retrofit.create(CovidAPI.class);
                Call<RecordsResponse> recordsResponseCall = covidAPI.getRecords();
                Response<RecordsResponse> recordsResponse = recordsResponseCall.execute();
                Result result = recordsResponse.body().getResult();
                List<Record> records = result.getRecords();

                int cases = getDailyCase(records);
                Log.d("MainActivity", "first element is " + records.get(0).getNotificationDate());
                Log.d("MainActivity", "size of records list is " + records.size());
                Log.d("MainActivity", "num of new cases is " + cases);

                return records;
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Record> records) {
            //updating the UI
            overallCases.setText(Integer.toString(records.size()));
            newCases.setText(Integer.toString(getDailyCase(records)));
            updated.setText("Information up-to-date as at " + records.get(0).getNotificationDate() + ". Dataset is updated daily at 8pm except on weekends");
        }
    }

    public int getDailyCase(List<Record> records){
        int numOfNewCases = 1;

        for (int i = 0; i < records.size(); i++){
            if(records.get(i).getNotificationDate().equals(records.get(i+1).getNotificationDate())){
                numOfNewCases++;
            }else{
                break;
            }
        }
        return numOfNewCases;
    }
}
