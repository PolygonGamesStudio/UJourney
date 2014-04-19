package com.PolygonGamesStudio.UJourney.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "UJourney";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

//    TODO: "NJ GBPLTW!!!!!!

    public static final String HISTORY_TABLE = "history";
    public static final String HT_ID = "id";
    public static final String HT_TITLE = "title";
    public static final String HT_DATE = "date";

    private static final String CREATE_HISORY_TABLE = "create table " + HISTORY_TABLE +
            " ( _id integer primary key autoincrement, " +
            HT_ID + " integer, " + HT_TITLE + " text, " + HT_DATE + " text )";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HISORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
