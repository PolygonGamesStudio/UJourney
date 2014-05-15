package com.PolygonGamesStudio.UJourney;

import android.app.ActionBar;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.PolygonGamesStudio.UJourney.Adapter.DrawerAdapter;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.NavigationDrawer.DrawerItemClickListener;
import com.PolygonGamesStudio.UJourney.SimpleCursorAdapter.JourneySimpleCursorAdapter;

public class HistoryListActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>  {

    SimpleCursorAdapter scAdapter;

    private static final String[] PROJECTION =  new  String[]{"_id", "title", "visit", "picture"};
    private static final int[] viewID =  new  int[]{R.id.textID, R.id.textTitle, R.id.textVisit, R.id.textPicture};

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_list);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        scAdapter = new JourneySimpleCursorAdapter(this, R.layout.history_list_item, null, PROJECTION, viewID, 0);
        ListView lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);

        initDrawer();

        registerForContextMenu(lvData);
        getLoaderManager().initLoader(0, null, this);

    }

    private void initDrawer(){
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);

        BaseAdapter drawer_adapter = new DrawerAdapter(HistoryListActivity.this);

        View header = getLayoutInflater().inflate(R.layout.drawer_list_header, null);
        mDrawerList.addHeaderView(header);

        mDrawerList.setAdapter(drawer_adapter);


        mTitle = mDrawerTitle = getTitle();
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.icon, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(HistoryListActivity.this, CacheContentProvider.HISTORY_CONTENT_URI, PROJECTION, null, null, null);
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
