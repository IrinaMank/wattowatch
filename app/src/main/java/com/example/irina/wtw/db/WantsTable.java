package com.example.irina.wtw.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.irina.wtw.model.Want;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WantsTable {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TMDB_ID = "tmdb_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_USER_ID = "userId";
    private static final String DATABASE_TABLE = "wants";
    private Context context;
    private SQLiteDatabase database;
    private SQLAdapter dbAdapter;

    public WantsTable(Context context){
        this.context = context;
    }

    public  WantsTable open(){
        dbAdapter = new SQLAdapter(context);
        database = dbAdapter.getWritableDatabase();
        return this;
    }

    public void close(){
        dbAdapter.close();
    }

    public long createWant(String title, Integer tmdbId, Date date, String userId){
        ContentValues value = new ContentValues();
        value.put(KEY_TITLE, title);
        value.put(KEY_TMDB_ID, tmdbId);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        value.put(KEY_DATE, formatter.format(date));
        value.put(KEY_USER_ID, userId);

        return database.insert(DATABASE_TABLE, null, value);
    }

    public Cursor fetchAllWants() {
        return database.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE, KEY_TMDB_ID,
                        KEY_DATE, KEY_USER_ID }, null, null, null,
                null, null, null);
    }

    public boolean deleteAllWants() {
        return database.delete(DATABASE_TABLE, null, null) > 0;
    }

    public boolean deleteWant(Want want) {
        return database.delete(DATABASE_TABLE, KEY_TMDB_ID + "=" + want.tmdbId, null) > 0;
    }
}
