package com.example.irina.wtw;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Irina on 08.07.2017.
 */

public class SQLAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "moviesDB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table movies (_id integer primary key autoincrement, "
            + "title text not null, image text not null, summary text not null);";


    public SQLAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS movies");
    }
}
