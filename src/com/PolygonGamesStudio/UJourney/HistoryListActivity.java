package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.PolygonGamesStudio.UJourney.ContentProvider.MyContactsProvider;

public class HistoryListActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>  {

    SimpleCursorAdapter scAdapter;
    LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    private static final String[] PROJECTION =  new  String[]{"_id", "name", "email"};
    private static final int[] viewID =  new  int[]{R.id.textName, R.id.textAge};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list);
        scAdapter = new SimpleCursorAdapter(this, R.layout.test_list_item, null, PROJECTION, viewID, 0);
        ListView lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);

        registerForContextMenu(lvData);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(HistoryListActivity.this, MyContactsProvider.CONTACT_CONTENT_URI, PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }

}
