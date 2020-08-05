package com.infs3605.infs3605;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class FavDBGenInfo extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "GenInfoDB";
    private static String TABLE_NAME = "favoriteGenInfoTable";
    public static String INFO_ID = "id";
    public static String INFO_TYPE = "infoType";
    public static String INFO_TITLE = "infoTitle";
    public static String INFO_DATE = "infoDate";
    public static String INFO_CONTENT = "infoContent";
    public static String INFO_STATUS = "infoStatus";

    private static String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + "("
            + INFO_ID + " TEXT,"  + INFO_TYPE + " TEXT,"
            + INFO_TITLE + " TEXT," + INFO_DATE + " TEXT," + INFO_CONTENT + " TEXT," +  INFO_STATUS+" TEXT)";

    public FavDBGenInfo(Context context){ super(context,DATABASE_NAME, null,DB_VERSION);}

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
            cv.put(INFO_ID, i);
            cv.put(INFO_STATUS, "0");

            db.insert(TABLE_NAME, null, cv);
        }
    }

    //insert data into database
    public void insertIntoTheDatabase(String id, String info_type, String info_title, String info_date, String info_content, String info_status){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(INFO_ID,id);
        cv.put(INFO_TYPE,info_type);
        cv.put(INFO_TITLE, info_title);
        cv.put(INFO_DATE, info_date);
        cv.put(INFO_CONTENT, info_content);
        cv.put(INFO_STATUS, info_status);
        db.insert(TABLE_NAME, null, cv);
        Log.d("genInfoDB Status", info_title +" , infoStatus - "+ info_status+" -. "+ cv);

    }

    //read all data
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor read_all_data(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME +" where "+ INFO_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }

    //remove line from database
    public void remove_fav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE "+ TABLE_NAME +" SET "+INFO_STATUS+" ='0' WHERE "+INFO_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }

        //select all favourite list
        public Cursor select_all_favourite_list(){
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT * FROM "+ TABLE_NAME +" WHERE "+INFO_STATUS+" ='1'";
            return db.rawQuery(sql,null);

    }
}
