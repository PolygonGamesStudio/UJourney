package com.PolygonGamesStudio.UJourney.ContentProvider;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

public class CacheContentProvider extends ContentProvider {

    public static final String DB_NAME = "mydb";
    public static final int DB_VERSION = 1;
    public static final String HISTORY_ID = "id";
    public static final String AUTHORITY = "cache";
    public static final String HISTORY_PATH = "history";
    public static final Uri HISTORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + HISTORY_PATH);
    public static final String CATEGORY_PATH = "category";
    public static final Uri CATEGORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CATEGORY_PATH);
    public static final String USER_PATH = "user";
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + USER_PATH);
    static final String HISTORY_TABLE = "history";
    static final String CATEGORY_TABLE = "category";
    static final String USER_TABLE = "user";
    static final String ROUTE_TABLE = "route";
    static final String NODE_TABLE = "node";


    static final String HISTORY_ID_ANDROID = "_id";
    static final String HISTORY_ID = "id";
    static final String HISTORY_TITLE = "title";
    static final String HISTORY_VISIT = "visit";
    static final String HISTORY_PICTURE = "picture";

    static final String ROUTE_ID_ANDROID = "_id";
    static final String ROUTE_ID = "id";
    static final String ROUTE_PLACE_NAME = "place_name";
    static final String ROUTE_ADDRESS = "address";
    static final String ROUTE_TIP = "tip";

    static final String NODE_ID_ANDROID = "_id";
    static final String NODE_ROUTE_ID = "route_id";
    static final String NODE_ID = "id";
    static final String NODE_TEXT = "text";
    static final String NODE_GEO = "geo";

    static final String DB_CREATE_HISTORY = "create table " + HISTORY_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, "
            + HISTORY_ID + " integer unique, "
            + HISTORY_TITLE + " text, "
            + HISTORY_PICTURE + " text, "
            + HISTORY_VISIT + " text" + ");";

    static final String DB_CREATE_CATEGORY = "create table " + CATEGORY_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, "
            + HISTORY_ID + " integer unique, "
            + HISTORY_TITLE + " text, "
            + HISTORY_PICTURE + " text);";

    static final String DB_CREATE_ROUTE = "create table " + ROUTE_TABLE + " ("
            + ROUTE_ID_ANDROID + " integer primary key autoincrement, "
            + ROUTE_ID + " integer unique, "
            + ROUTE_PLACE_NAME + " text, "
            + ROUTE_ADDRESS + " text, "
            + ROUTE_TIP + " text);";

    static final String DB_CREATE_NODE = "create table " + NODE_TABLE + " ("
            + NODE_ID_ANDROID + " integer primary key autoincrement, "
            + NODE_ROUTE_ID + " integer, "
            + NODE_ID + " integer unique, "
            + NODE_TEXT + " text, "
            + NODE_GEO + " text);";

    public static final String AUTHORITY = "cache";

    public static final String HISTORY_PATH = "history";
    public static final String CATEGORY_PATH = "category";
    public static final String ROUTE_PATH = "route";
    public static final String NODE_PATH = "node";

    public static final Uri HISTORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + HISTORY_PATH);
    public static final Uri CATEGORY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CATEGORY_PATH);
    public static final Uri ROUTE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + ROUTE_PATH);
    public static final Uri NODE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NODE_PATH);


    static final String USER_ID = "id";
    static final String USER_NAME = "name";
    static final String LASTUSER_NAME = "lastname";
    static final String USER_PICTURE = "picture";
    static final String DB_CREATE_USER = "create table " + USER_TABLE + " ("
            + HISTORY_ID_ANDROID + " integer primary key autoincrement, "
            + USER_ID + " integer unique, "
            + USER_NAME + " text, "
            + LASTUSER_NAME + " text, "
            + USER_PICTURE + " text);";
    static final int URI_HISTORY = 1;
    static final int URI_CATEGORY = 2;
    static final int URI_ROUTE = 3;
    static final int URI_NODE = 4;
    static final int URI_USER = 5;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, HISTORY_PATH, URI_HISTORY);
        uriMatcher.addURI(AUTHORITY, CATEGORY_PATH, URI_CATEGORY);
        uriMatcher.addURI(AUTHORITY, ROUTE_PATH, URI_ROUTE);
        uriMatcher.addURI(AUTHORITY, NODE_PATH, URI_NODE);
        uriMatcher.addURI(AUTHORITY, USER_PATH, URI_USER);
    }
    public Uri dbHelperUri;
    DBHelper dbHelper;
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
            case URI_ROUTE:
                Cursor cursorRoute = db.query(ROUTE_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursorRoute.setNotificationUri(getContext().getContentResolver(), ROUTE_CONTENT_URI);
                return cursorRoute;
            case URI_NODE:
                Cursor cursorNode = db.query(NODE_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursorNode.setNotificationUri(getContext().getContentResolver(), NODE_CONTENT_URI);
                return cursorNode;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case URI_HISTORY:
                db = dbHelper.getWritableDatabase();
                // TODO: android.database.sqlite.SQLiteConstraintException схватить ошибку, что-бы не срала в логи
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
                    long rowCategoryID = db.insert(CATEGORY_TABLE, null, values);
                    Uri resultCategoryUri = ContentUris.withAppendedId(CATEGORY_CONTENT_URI, rowCategoryID);
                    getContext().getContentResolver().notifyChange(resultCategoryUri, null);
                    return resultCategoryUri;
                } catch (SQLiteConstraintException e) {
                    return null;
                }
            case URI_USER:
                try {
                    long rowUserID = db.insert(USER_TABLE, null, values);
                    Uri resultUserUri = ContentUris.withAppendedId(CATEGORY_CONTENT_URI, rowUserID);
                    getContext().getContentResolver().notifyChange(resultUserUri, null);
                    return resultUserUri;
                } catch (SQLiteConstraintException e) {
                    return null;
                }
                // TODO: android.database.sqlite.SQLiteConstraintException схватить ошибку, что-бы не срала в логи
            case URI_ROUTE:
                long rowRouteID = db.insert(ROUTE_TABLE, null, values);
                Uri resultRouteUri = ContentUris.withAppendedId(ROUTE_CONTENT_URI, rowRouteID);
                getContext().getContentResolver().notifyChange(resultRouteUri, null);
                return resultRouteUri;
            case URI_NODE:
                long rowNodeID = db.insert(NODE_TABLE, null, values);
                Uri resultNodeUri = ContentUris.withAppendedId(NODE_CONTENT_URI, rowNodeID);
                getContext().getContentResolver().notifyChange(resultNodeUri, null);
                return resultNodeUri;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case URI_USER:
                try {
                    long rowUserID = db.delete(USER_TABLE, selection, selectionArgs);
                    Uri resultUserUri = ContentUris.withAppendedId(USER_CONTENT_URI, rowUserID);
                    getContext().getContentResolver().notifyChange(resultUserUri, null);
                    return 1;
                } catch (SQLiteConstraintException e) {
                    return 0;
                }
            case URI_HISTORY:
                try {
                    long rowHistoryId = db.delete(HISTORY_TABLE, selection, selectionArgs);
                    Uri resultHistoryUri = ContentUris.withAppendedId(HISTORY_CONTENT_URI, rowHistoryId);
                    getContext().getContentResolver().notifyChange(resultHistoryUri, null);
                    return 1;
                }
                catch (SQLiteConstraintException e) {
                    return 0;
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
            db.execSQL(DB_CREATE_HISTORY);
            db.execSQL(DB_CREATE_CATEGORY);
            db.execSQL(DB_CREATE_ROUTE);
            db.execSQL(DB_CREATE_NODE);
            db.execSQL(DB_CREATE_USER);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
