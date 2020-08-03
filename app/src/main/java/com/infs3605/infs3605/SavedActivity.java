package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavDB favDB;
    private ArrayList<FavArticle> favArticleList = new ArrayList<>();
    private SavedActivityAdapter favAdapter;
    private Context context;

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
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(SavedActivity.this, GeneralInfoActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.industries:
                        Intent intent4 = new Intent(SavedActivity.this, MainActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.liveUpdates:
                        Intent intent2 = new Intent(SavedActivity.this, LiveUpdatesActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.schemes:
                        Intent intent3 = new Intent(SavedActivity.this, SchemesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.saved:

                        break;

                }
                return false;
            }
        });

        favDB = new FavDB(this);
        recyclerView = (RecyclerView) findViewById(R.id.saved_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        loadData();

    }

    private void loadData(){
        if (favArticleList != null){
            favArticleList.clear();
        }

        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_favourite_list();
        try{
            while (cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_TITLE));
                String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                String date = cursor.getString(cursor.getColumnIndex(FavDB.ARTICLE_DATE));
                FavArticle favArticle = new FavArticle(id, title, date);
                favArticleList.add(favArticle);
            }
        }finally{
            if(cursor != null && cursor.isClosed()){
                cursor.close();
                db.close();
            }

            favAdapter = new SavedActivityAdapter(this, favArticleList);

            recyclerView.setAdapter(favAdapter);
        }
    }
}