package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class SavedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ArticleClickInterface {

    private RecyclerView recyclerView;
    private FavDB favDB;
    private FavDBGenInfo favDBGenInfo;
    private ArrayList<FavArticle> favArticleList = new ArrayList<>();
    private ArrayList<FavArticle> favGenInfoList = new ArrayList<>();
    private SavedActivityAdapter favAdapter;
    private Context context;
    private Spinner savedSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationPane);
        //checks/selects the bottom icons as they are clicked
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent intent1 = new Intent(SavedActivity.this, GeneralInfoActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.industries:
                        Intent intent4 = new Intent(SavedActivity.this, MainActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.liveUpdates:
                        Intent intent2 = new Intent(SavedActivity.this, LiveUpdatesActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.schemes:
                        Intent intent3 = new Intent(SavedActivity.this, SchemesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.saved:

                        break;

                }
                return false;
            }
        });

        favDB = new FavDB(this);
        favDBGenInfo = new FavDBGenInfo(this);
        savedSpinner = findViewById(R.id.saved_spinner);
        recyclerView = (RecyclerView) findViewById(R.id.saved_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ArrayList<String> savedSpinnerEntries = new ArrayList<>();
        savedSpinnerEntries.add("Restrictions articles");
        savedSpinnerEntries.add("General Information articles");

        ArrayAdapter<String> savedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, savedSpinnerEntries);
        savedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        savedSpinner.setAdapter(savedAdapter);
        savedSpinner.setOnItemSelectedListener(this);


//        loadData();

    }

//    private void loadData(){
//        if (favArticleList != null){
//            favArticleList.clear();
//        }
//
//        SQLiteDatabase db = favDB.getReadableDatabase();
//        Cursor cursor = favDB.select_all_favourite_list();
//        try{
//            while (cursor.moveToNext()){
//                String title = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_TITLE));
//                String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
//                String date = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_DATE));
//                FavArticle favArticle = new FavArticle(id, title, date);
//                favArticleList.add(favArticle);
//            }
//        }finally{
//            if(cursor != null && cursor.isClosed()){
//                cursor.close();
//                db.close();
//            }
//
//            favAdapter = new SavedActivityAdapter(this, favArticleList);
//
//            recyclerView.setAdapter(favAdapter);
//        }
//    }

    String savedArticleType;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        savedArticleType = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), savedArticleType, Toast.LENGTH_SHORT).show();

        getSelectedSavedType(savedArticleType);
    }

    private void getSelectedSavedType(String savedArticleType) {
        if (savedArticleType.equalsIgnoreCase("restrictions articles")) {
            ////

            if (favArticleList != null) {
                favArticleList.clear();
            }

            SQLiteDatabase db = favDB.getReadableDatabase();
            Cursor cursor = favDB.select_all_favourite_list();
            try {
                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_TITLE));
                    String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                    String date = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_DATE));
                    FavArticle favArticle = new FavArticle(id, title, date);
                    favArticleList.add(favArticle);
                }
            } finally {
                if (cursor != null && cursor.isClosed()) {
                    cursor.close();
                    db.close();
                }

                favAdapter = new SavedActivityAdapter(this, favArticleList, savedArticleType, this);

                recyclerView.setAdapter(favAdapter);
            }


        } else {
            if (savedArticleType.equalsIgnoreCase("general information articles")) {

                if (favGenInfoList != null) {
                    favGenInfoList.clear();
                }

                SQLiteDatabase db = favDBGenInfo.getReadableDatabase();
                Cursor cursor = favDBGenInfo.select_all_favourite_list();
                try {
                    while (cursor.moveToNext()) {
                        String title = cursor.getString(cursor.getColumnIndex(FavDBGenInfo.INFO_TITLE));
                        String id = cursor.getString(cursor.getColumnIndex(FavDBGenInfo.INFO_ID));
                        String date = cursor.getString(cursor.getColumnIndex(FavDBGenInfo.INFO_DATE));
                        FavArticle favArticle = new FavArticle(id, title, date);
                        favGenInfoList.add(favArticle);
                    }
                } finally {
                    if (cursor != null && cursor.isClosed()) {
                        cursor.close();
                        db.close();
                    }

                    favAdapter = new SavedActivityAdapter(this, favGenInfoList, savedArticleType, this);

                    recyclerView.setAdapter(favAdapter);
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onArticleClick(int position) {

        if (savedArticleType.equalsIgnoreCase("restrictions articles")) {
            Cursor cursor = favDB.read_all_data(favArticleList.get(position).getKey_id());
            Log.d("SavedActivity", "curosr " + cursor.toString());
            SQLiteDatabase db = favDB.getReadableDatabase();

            try {
                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_TITLE));
                    String date = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_DATE));
                    String content = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_CONTENT));
                    ArrayList<String> article = new ArrayList<String>();
                    article.add(title);
                    article.add(date);
                    article.add(content);

                    Intent intent = new Intent(this, ArticleDetail.class);
                    intent.putStringArrayListExtra("article", article);
                    startActivity(intent);
                }
            } finally {
                if (cursor != null && cursor.isClosed()) {
                    cursor.close();
                    db.close();
                }
            }


        } else {
            if (savedArticleType.equalsIgnoreCase("general information articles")) {
                Cursor cursor = favDBGenInfo.read_all_data(favGenInfoList.get(position).getKey_id());
                SQLiteDatabase db = favDBGenInfo.getReadableDatabase();

                try {
                    while (cursor.moveToNext()) {
                        String title = cursor.getString(cursor.getColumnIndex(FavDBGenInfo.INFO_TITLE));
                        String date = cursor.getString(cursor.getColumnIndex(FavDBGenInfo.INFO_DATE));
                        String content = cursor.getString(cursor.getColumnIndex(FavDBGenInfo.INFO_CONTENT));
                        ArrayList<String> article = new ArrayList<String>();
                        article.add(title);
                        article.add(date);
                        article.add(content);

                        Intent intent = new Intent(this, GeneralInfoDetailActivity.class);
                        intent.putStringArrayListExtra("article", article);
                        startActivity(intent);
                    }
                } finally {
                    if (cursor != null && cursor.isClosed()) {
                        cursor.close();
                        db.close();
                    }

                }
            }

        }
    }
}