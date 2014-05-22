package com.PolygonGamesStudio.UJourney;

import android.app.ActionBar;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.SimpleCursorAdapter.JourneySimpleCursorAdapter;

public class CategoryListActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>  {

    SimpleCursorAdapter scAdapter;

    private static final String[] PROJECTION =  new  String[]{"_id", "title", "picture"};
    private static final int[] viewID =  new  int[]{R.id.histElId, R.id.histElTitle, R.id.histElPicture};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        scAdapter = new JourneySimpleCursorAdapter(this, R.layout.category_list_item, null, PROJECTION, viewID, 0);
        ListView lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);

        registerForContextMenu(lvData);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(CategoryListActivity.this, CacheContentProvider.CATEGORY_CONTENT_URI, PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}

