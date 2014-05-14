package com.PolygonGamesStudio.UJourney.NavigationDrawer;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.R;

public class DrawerItems extends Activity {

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    public DrawerItems(Activity activity)  {
        String[] mActivityList = getResources().getStringArray(R.array.activity_list);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(activity, R.layout.drawer_list_item, mActivityList));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener(activity));
        this.mTitle = this.mDrawerTitle = getTitle();

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout,
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
}
