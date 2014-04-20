package com.PolygonGamesStudio.UJourney;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import com.PolygonGamesStudio.UJourney.ContentProvider.MyContactsProvider;

/**
 * Created by user1 on 4/20/14.
 */
public class LoaderCallbacksTest extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;
    private static final int LOADER_ID = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list);


        int[] viewsID = {R.id.textName, R.id.textAge};

        String[] projection = {"_id", "name", "email" };

        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.test_list_item, null, projection, viewsID, 0);

        setListAdapter(mAdapter);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {"_id", "name", "email" };
        return new CursorLoader(LoaderCallbacksTest.this, MyContactsProvider.CONTACT_CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LOADER_ID:
//              TODO:  java.lang.NullPointerException -> mAdapter
                mAdapter.swapCursor(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
