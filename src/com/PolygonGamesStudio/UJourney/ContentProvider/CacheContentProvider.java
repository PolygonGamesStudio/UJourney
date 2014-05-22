package com.PolygonGamesStudio.UJourney.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class CacheContentProvider extends ContentProvider {

    public static final String DB_NAME = "mydb";
    public static final int DB_VERSION = 1;

    static final String HISTORY_TABLE = "history";
    static final String CATEGORY_TABLE = "category";
    static final String USER_TABLE = "user";

    static final String HISTORY_ID_ANDROID = "_id";
    static final String HISTORY_ID = "id";
    static final String HISTORY_TITLE = "title";
    static final String HISTORY_VISIT = "visit";
    static final String HISTORY_PICTURE = "picture";

    static final String USER_ID = "id";
    static final String USER_NAME = "name";
    static final String LASTUSER_NAME = "lastname";
    static final String USER_PICTURE = "picture";



    static final String DB_CREATE_HISTORY = "create table " + HISTORY_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, " +
            HISTORY_ID + " integer unique, " +
            HISTORY_TITLE + " text, " +
            HISTORY_PICTURE + " text, " +
            HISTORY_VISIT + " text);";

    static final String DB_CREATE_CATEGORY = "create table " + CATEGORY_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, "
            + HISTORY_ID + " integer unique, " + HISTORY_TITLE + " text, " + HISTORY_PICTURE + " text);";

    static final String DB_CREATE_USER = "create table " + USER_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, "
            + USER_ID + " integer unique, "
            + USER_NAME + " text, "
            + LASTUSER_NAME + " text, "
            + USER_PICTURE + " text);";

    public static final String AUTHORITY = "cache";

    public static final String HISTORY_PATH = "history";
    public static final String CATEGORY_PATH = "category";
    public static final String USER_PATH = "user";

    public static final Uri HISTORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + HISTORY_PATH);
    public static final Uri CATEGORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CATEGORY_PATH);
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + USER_PATH);

    static final int URI_HISTORY = 1;
    static final int URI_CATEGORY = 2;
    static final int URI_USER = 3;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, HISTORY_PATH, URI_HISTORY);
        uriMatcher.addURI(AUTHORITY, CATEGORY_PATH, URI_CATEGORY);
        uriMatcher.addURI(AUTHORITY, USER_PATH, URI_USER);
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
            case URI_USER:
                Cursor cursorUser = db.query(USER_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursorUser.setNotificationUri(getContext().getContentResolver(), USER_CONTENT_URI);
                return cursorUser;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case URI_HISTORY:
                db = dbHelper.getWritableDatabase();
                // TODO: перезаписывать при повторе
                try {
                    long rowHistoryID = db.insert(HISTORY_TABLE, null, values);
                    Uri resultHistoryUri = ContentUris.withAppendedId(HISTORY_CONTENT_URI, rowHistoryID);
                    getContext().getContentResolver().notifyChange(resultHistoryUri, null);
                    return resultHistoryUri;
                } catch (SQLiteConstraintException e) {
                    return null;
                }


            case URI_CATEGORY:
                try {
                    db = dbHelper.getWritableDatabase();
                    long rowCategoryID = db.insert(CATEGORY_TABLE, null, values);
                    Uri resultCategoryUri = ContentUris.withAppendedId(CATEGORY_CONTENT_URI, rowCategoryID);
                    getContext().getContentResolver().notifyChange(resultCategoryUri, null);
                    return resultCategoryUri;
                } catch (SQLiteConstraintException e) {
                    return null;
                }
            case URI_USER:
                try {
                    db = dbHelper.getWritableDatabase();
                    long rowUserID = db.insert(USER_TABLE, null, values);
                    Uri resultUserUri = ContentUris.withAppendedId(CATEGORY_CONTENT_URI, rowUserID);
                    getContext().getContentResolver().notifyChange(resultUserUri, null);
                    return resultUserUri;
                } catch (SQLiteConstraintException e) {
                    return null;
                }
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_USER:
                try {
                    db = dbHelper.getWritableDatabase();
                    long rowUserID = db.delete(USER_TABLE, selection, selectionArgs);
                    Uri resultUserUri = ContentUris.withAppendedId(USER_CONTENT_URI, rowUserID);
                    getContext().getContentResolver().notifyChange(resultUserUri, null);
                    return 1;
                } catch (SQLiteConstraintException e) {
                    return 1;
                }
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
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
//          TODO: перепиши меня!
            db.execSQL(DB_CREATE_HISTORY);
            db.execSQL(DB_CREATE_CATEGORY);
            db.execSQL(DB_CREATE_USER);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
