package com.example.irina.wtw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MoviesTable {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_SUMMARY = "summary";
    private static final String DATABASE_TABLE = "movies";
    private Context context;
    private SQLiteDatabase database;
    private SQLAdapter dbAdapter;

    public MoviesTable(Context context){
        this.context = context;
    }

    public  MoviesTable open(){
        dbAdapter = new SQLAdapter(context);
        database = dbAdapter.getWritableDatabase();
        return this;
    }

    public void close(){
        dbAdapter.close();
    }

    public long createMovie(String title, String image, String summary){
        ContentValues value = new ContentValues();
        value.put(KEY_TITLE, title);
        value.put(KEY_SUMMARY, summary);
        value.put(KEY_IMAGE, image);

        return database.insert(DATABASE_TABLE, null, value);
    }

    public Cursor fetchAllMovies() {
        return database.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE, KEY_IMAGE,
                        KEY_SUMMARY }, null, null, null,
                null, null, null);
    }

    public boolean deleteAllMovies() {
        return database.delete(DATABASE_TABLE, null, null) > 0;

    }


}
