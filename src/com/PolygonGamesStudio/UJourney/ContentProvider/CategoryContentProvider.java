package com.PolygonGamesStudio.UJourney.ContentProvider;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

public class CategoryContentProvider extends ContentProvider {

    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    static final String CONTACT_TABLE = "category";

    static final String CONTACT_ID_ANDROID = "_id";
    static final String CONTACT_ID = "id";
    static final String CONTACT_TITLE = "title";
    static final String CONTACT_PICTURE = "picture";

    static final String DB_CREATE = "create table " + CONTACT_TABLE + "("
            + CONTACT_ID_ANDROID + " integer primary key autoincrement, "
            +CONTACT_ID + " integer unique, " + CONTACT_TITLE + " text, " + CONTACT_PICTURE + " text" + ");";

    static final String AUTHORITY = "category";

    static final String CONTACT_PATH = "category";

    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CONTACT_PATH);

    static final int URI_CONTACTS = 1;

    static final int URI_CONTACTS_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CONTACTS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS: // общий Uri
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = CONTACT_TITLE + " ASC";
                }
                break;
            case URI_CONTACTS_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID_ANDROID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID_ANDROID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(CONTACT_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != URI_CONTACTS)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
//      TODO: android.database.sqlite.SQLiteConstraintException схватить ошибку, что-бы не срала в логи
        long rowID = db.insert(CONTACT_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
