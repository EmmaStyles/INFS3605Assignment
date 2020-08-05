package com.infs3605.infs3605;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class FavDB extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "ArticleDB";
    private static String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";
    public static String ARTICLE_INDUSTRY = "articleIndustry";
    public static String ARTICLE_SEGMENT = "articleSegment";
    public static String ARTICLE_TITLE = "articleTitle";
    public static String ARTICLE_DATE = "articleDate";
    public static String ARTICLE_CONTENT = "articleContent";
    public static String FAVOURITE_STATUS = "fStatus";

    private static String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT," + ARTICLE_INDUSTRY + " TEXT," + ARTICLE_SEGMENT + " TEXT,"
            + ARTICLE_TITLE + " TEXT," + ARTICLE_DATE + " TEXT," + ARTICLE_CONTENT + " TEXT," +  FAVOURITE_STATUS+" TEXT)";

    public FavDB(Context context){ super(context,DATABASE_NAME, null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //create an empty table
    public void insertEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for(int i = 1; i<40; i++){
            cv.put(KEY_ID, i);
            cv.put(FAVOURITE_STATUS, "0");

            db.insert(TABLE_NAME, null, cv);
        }
    }

    //insert data into database
    public void insertIntoTheDatabase(String id, String article_industry, String article_segment, String article_title, String article_date, String article_content, String fav_status){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID,id);
        cv.put(ARTICLE_INDUSTRY, article_industry);
        cv.put(ARTICLE_SEGMENT,article_segment);
        cv.put(ARTICLE_TITLE, article_title);
        cv.put(ARTICLE_DATE, article_date);
        cv.put(ARTICLE_CONTENT, article_content);
        cv.put(FAVOURITE_STATUS, fav_status);
        db.insert(TABLE_NAME, null, cv);
        Log.d("FavDB Status", article_title +" , favstatus - "+ fav_status+" -. "+ cv);

    }

    //read all data
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor read_all_data(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME +" where "+ KEY_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }

    //remove line from database
    public void remove_fav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE "+ TABLE_NAME +" SET "+FAVOURITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }

    //select all favourite list
    public Cursor select_all_favourite_list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+ TABLE_NAME +" WHERE "+FAVOURITE_STATUS+" ='1'";
        return db.rawQuery(sql,null);

    }

}
