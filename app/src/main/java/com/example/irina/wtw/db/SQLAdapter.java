package com.example.irina.wtw.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wantsDB";
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_CREATE = "create table wants (_id integer primary key autoincrement, "
            + "title text not null, tmdb_id text not null unique, date text not null, userId text not null);";


    SQLAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS wants");
        onCreate(sqLiteDatabase);
    }
}
