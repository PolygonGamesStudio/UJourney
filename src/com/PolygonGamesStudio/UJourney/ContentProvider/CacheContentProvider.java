package com.PolygonGamesStudio.UJourney.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

public class CacheContentProvider extends ContentProvider {

    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    static final String HISTORY_TABLE = "history";
    static final String CATEGORY_TABLE = "category";

    static final String HISTORY_ID_ANDROID = "_id";
    static final String HISTORY_ID = "id";
    static final String HISTORY_TITLE = "title";
    static final String HISTORY_VISIT = "visit";
    static final String HISTORY_PICTURE = "picture";

    static final String DB_CREATE_HISTORY = "create table " + HISTORY_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, "
            + HISTORY_ID + " integer unique, " + HISTORY_TITLE + " text, " + HISTORY_PICTURE + " text, " + HISTORY_VISIT
            + " text" + ");";

    static final String DB_CREATE_CATEGORY = "create table " + CATEGORY_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, "
            + HISTORY_ID + " integer unique, " + HISTORY_TITLE + " text, " + HISTORY_PICTURE + " text);";

    public static final String AUTHORITY = "cache";

    public static final String HISTORY_PATH = "history";
    public static final String CATEGORY_PATH = "category";

    public static final Uri HISTORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + HISTORY_PATH);
    public static final Uri CATEGORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CATEGORY_PATH);

    static final int URI_HISTORY = 1;
    static final int URI_CATEGORY = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, HISTORY_PATH, URI_HISTORY);
        uriMatcher.addURI(AUTHORITY, CATEGORY_PATH, URI_CATEGORY);
    }

    DBHelper dbHelper;
    public Uri dbHelperUri;

    SQLiteDatabase db;

    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    // чтение
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        dbHelperUri = uri;
        db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case URI_HISTORY:
                Cursor cursorHistory = db.query(HISTORY_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursorHistory.setNotificationUri(getContext().getContentResolver(), HISTORY_CONTENT_URI);
                return cursorHistory;
            case URI_CATEGORY:
                Cursor cursorCategory = db.query(CATEGORY_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursorCategory.setNotificationUri(getContext().getContentResolver(), CATEGORY_CONTENT_URI);
                return cursorCategory;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case URI_HISTORY:
                db = dbHelper.getWritableDatabase();
                // TODO: android.database.sqlite.SQLiteConstraintException схватить ошибку, что-бы не срала в логи
                // TODO: перезаписывать при повторе
                long rowHistoryID = db.insert(HISTORY_TABLE, null, values);
                Uri resultHistoryUri = ContentUris.withAppendedId(HISTORY_CONTENT_URI, rowHistoryID);
                getContext().getContentResolver().notifyChange(resultHistoryUri, null);
                return resultHistoryUri;
            case URI_CATEGORY:
                db = dbHelper.getWritableDatabase();
                // TODO: android.database.sqlite.SQLiteConstraintException схватить ошибку, что-бы не срала в логи
                long rowCategoryID = db.insert(CATEGORY_TABLE, null, values);
                Uri resultCategoryUri = ContentUris.withAppendedId(CATEGORY_CONTENT_URI, rowCategoryID);
                getContext().getContentResolver().notifyChange(resultCategoryUri, null);
                return resultCategoryUri;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_HISTORY);
            db.execSQL(DB_CREATE_CATEGORY);
//            switch (uriMatcher.match(dbHelperUri)) {
//                case URI_HISTORY:
//                    db.execSQL(DB_CREATE_HISTORY);
//                    break;
//                case URI_CATEGORY:
//                    db.execSQL(DB_CREATE_CATEGORY);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Wrong URI: " + dbHelperUri);
//            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
