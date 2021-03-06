package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.PolygonGamesStudio.UJourney.Adapter.DrawerAdapter;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.PolygonGamesStudio.UJourney.NavigationDrawer.DrawerItemClickListener;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;
import com.PolygonGamesStudio.UJourney.SimpleCursorAdapter.ProfileHistorySimpleCursorAdapter;
import com.squareup.picasso.Picasso;

public class PlaceActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private ListView mDrawerList;

    SimpleCursorAdapter scAdapter;
    private static final String[] PROJECTION =  new  String[]{"_id", "title", "visit", "picture"};
    private static final int[] viewID =  new  int[]{R.id.textID, R.id.headerTextView, R.id.descriptionTextView, R.id.headerImageView};

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);

        initDrawer();

//        ImageView imageView = (ImageView)findViewById(R.id.place_background);
//        imageView.setColorFilter(202024);

        Picasso.with(this).load(R.drawable.starbucks_logo)
                .transform(PicassoHelper.getTransform())
                .into((ImageView) findViewById(R.id.place_logo));


        ListView lvPlaces = (ListView) findViewById(R.id.place_history_ListView);
        View header = getLayoutInflater().inflate(R.layout.built_in_history_list_header, null);
        lvPlaces.addHeaderView(header);

        scAdapter = new ProfileHistorySimpleCursorAdapter(this, R.layout.built_in_history_list_item, null, PROJECTION, viewID, 0);
        ListView lvData = (ListView) findViewById(R.id.place_history_ListView);
        lvData.setAdapter(scAdapter);

        Intent service_history = new Intent(this, HistoryService.class);
        this.startService(service_history);

        registerForContextMenu(lvData);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(PlaceActivity.this, CacheContentProvider.HISTORY_CONTENT_URI, PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }

    private void initDrawer(){
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        BaseAdapter drawer_adapter = new DrawerAdapter(this);
        View header = getLayoutInflater().inflate(R.layout.drawer_list_header, null);
        ImageView v = (ImageView) header.findViewById(R.id.headerImageView);
        Picasso.with(this).load(R.drawable.avatar_logout).transform(PicassoHelper.getTransform()).into(v);
        mDrawerList.addHeaderView(header);

        mDrawerList.setAdapter(drawer_adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener(this));
        mTitle = mDrawerTitle = getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.icon, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
}
