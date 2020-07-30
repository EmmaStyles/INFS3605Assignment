package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.logging.FileHandler;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;


//when an industry is clicked on the main activity, it opens up this activity
public class IndustryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ArticleClickInterface {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private IndustryActivityAdapter mIndustryActAdapter;
    private RecyclerView.LayoutManager layoutManager;
    AsyncHttpClient client;
    String excelUrl = "https://raw.githubusercontent.com/EmmaStyles/INFS3605Assignment/master/industryData.xls";
    Workbook workbook;
    ArrayList<Article> data = new ArrayList<Article>();

    //    ArrayList<String> industries, segments, titles, dates, contents, images;
//    ArrayList<ArrayList<String>> list = new ArrayList<>();
    TextView industryHeader;
    ProgressBar progressBar;
    IndustryClass industryClass;
    IndustryClass industryClass2;
    ArrayList<String> industrySegments;
    IndustryClass industryClassFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);
        Log.d("IndustryActivity", "IndustryActivity was created");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progress_bar);
//
        this.setTitle("CovidAware");
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationPane);

        //checks/selects readData();the bottom icons as they are clicked
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent intent1 = new Intent(IndustryActivity.this, GeneralInfoActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.industries:
                        Intent intent4 = new Intent(IndustryActivity.this, MainActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.liveUpdates:
                        Intent intent2 = new Intent(IndustryActivity.this, LiveUpdatesActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.schemes:
                        Intent intent3 = new Intent(IndustryActivity.this, SchemesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });


        spinner = findViewById(R.id.industrySegment_spinner);
        recyclerView = (RecyclerView) findViewById(R.id.rv_industry_activity);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        industryClass = intent.getParcelableExtra("Industry Object");

        industryHeader = findViewById(R.id.indsutry_chosen_TV);
        industryHeader.setText(industryClass.getIndustryName());


        ArrayList<String> industrySegments = new ArrayList<>();
        industrySegments.add("All");

        //Fills the ArrayList industrySegments with segments that come from the parcelable Industry Object clicked on

        for (int i = 0; i < industryClass.industrySegments.size(); i++) {
            String segment = industryClass.industrySegments.get(i);
            industrySegments.add(segment);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, industrySegments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        recyclerView.setLayoutManager(layoutManager);

        readData();

    }


    private ArrayList<Article> getArticles() {

        data.clear();


        data.add(new Article("Travel", "Hotels", "Accommodation, Holiday HomeS & Holiday Rentals", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
        data.add(new Article("Property", "Real Estate", "Auction houses (other than Clearing houses)", "23/06/2020", "Accommodation services may be offered without restriction, except holiday rentals which are subject to restrictions.\n"));
        data.add(new Article("Hospitality", "Bars", "Bars, pubs, clubs, cellar doors, micro-breweries, distilleries and casinos", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres (excluding staff) per existing separate seated food or drink area.\n"));
        data.add(new Article("Beauty", "Nail Salons", "Beauty, nail, tanning and waxing salons, tattoo and massage parlours and spas", "23/06/2020", "Capacity must not exceed 20 customers or one customer per 4 square metres.\n"));
        data.add(new Article("Hospitality", "Restaurants and Cafes", "Cafes and restaurants", "23/06/2020", "Capacity must not exceed 50 customers or one customer per 4 square metres.\n"));
        data.add(new Article("Hospitality", "Restaurants and Cafes", "Food Courts", "23/06/2020", "Operators must have a COVID safety plan\n"));
        data.add(new Article("Beauty", "Hairdressers", "Hairdresses and barbers", "23/06/2020", "Hairdressing salons and barbers may open with restrictions.\n"));
        Log.d("TAG", "OnSuccess1: " + data.get(0));


        return data;

    }

    ArrayList<Article> articlesToDisplay = new ArrayList<Article>();

    private void getSelectedSegment(String segment) {
//        data.clear();
//try read data now

        String industryChosen = industryClass.getIndustryName();
        // changed this to find industry
        Log.d("IndustryActivity", "industryChosen " + industryChosen);
        if (segment.equalsIgnoreCase("All")) {

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getIndustry().equalsIgnoreCase(industryChosen)) {
                    articlesToDisplay.add(new Article(data.get(i).getIndustry(), data.get(i).getSegment(), data.get(i).getTitle(), data.get(i).getDate(), data.get(i).getContent()));
                } else {

                }

            }
            Log.d("TAG", "OnSuccess3: " + articlesToDisplay.size());
            mIndustryActAdapter = new IndustryActivityAdapter(articlesToDisplay, this, this);


        } else {
            articlesToDisplay.clear();
            for (int i = 0; i < data.size(); i++) {
//            for(Article article : getArticles()){
                if (data.get(i).getSegment().equalsIgnoreCase(segmentChosen)) {
                    articlesToDisplay.add(new Article(data.get(i).getIndustry(), data.get(i).getSegment(), data.get(i).getTitle(), data.get(i).getDate(), data.get(i).getContent()));
                }
            }
            Log.d("TAG", "OnSuccess4: the size of the articlestodisplay list is " + articlesToDisplay.size());

            mIndustryActAdapter = new IndustryActivityAdapter(articlesToDisplay, this, this);
        }
        recyclerView.setAdapter(mIndustryActAdapter);

    }


    String segmentChosen;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        segmentChosen = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), segmentChosen, Toast.LENGTH_SHORT).show();

        Log.d("IndustryActivity", "segmentChosen is " + segmentChosen);

        getSelectedSegment(segmentChosen);



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onArticleClick(int position) {
        Intent intent = new Intent(this, ArticleDetail.class);
        intent.putExtra("Article Object", articlesToDisplay.get(position));

        startActivity(intent);
    }


    public void readData() {
        // method to access data in the excel file

        data.clear();

        client = new AsyncHttpClient();
        client.get(excelUrl, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(IndustryActivity.this, "Download Failed", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
                Log.d("TAG", "OnFail: failed download");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Toast.makeText(IndustryActivity.this, "File Downloaded", Toast.LENGTH_SHORT).show();

                Log.d("TAG", "OnSuccess1: downloaded");
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if (file != null) {
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for (int i = 0; i < sheet.getRows(); i++) {

                            Cell[] row = sheet.getRow(i);
//                            industries.add(row[0].getContents());
//                            segments.add(row[1].getContents());
//                            titles.add(row[2].getContents());
//                            dates.add(row[3].getContents());
//                            contents.add(row[4].getContents());
//
//                            images.add(row[5].getContents());

                            data.add(new Article(row[0].getContents(), row[1].getContents(), row[2].getContents(), row[3].getContents(), row[4].getContents()));

                            Log.d("IndustryActivity", "Just created " + data.get(i).getIndustry());
                        }
                        Log.d("IndustryActivity", "outside try statement " + data.size());



                        // first data is the second item since it is the first one is the header of the column
//                        Log.d("Outside the for loop", "first object in the data list is " + data.get(1).getIndustry());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }

                    getSelectedSegment(segmentChosen);
//

                }

                //try do adapter stuff in this portion here
            }
        });


    }

//use synchronous call

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_function, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Search Title");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // not doing anything here since we want the list to be filtered in real time
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mIndustryActAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }


}
