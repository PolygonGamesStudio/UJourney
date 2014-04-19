package com.PolygonGamesStudio.UJourney;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class HistoryListActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    SimpleCursorAdapter mAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    private static final String[] PROJECTION =  new  String[]{"_id", "title", "date"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
