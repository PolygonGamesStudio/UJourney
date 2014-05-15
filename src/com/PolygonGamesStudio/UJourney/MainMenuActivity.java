package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.PolygonGamesStudio.UJourney.Adapter.DrawerAdapter;
import com.PolygonGamesStudio.UJourney.Adapter.MainMenuAdapter;
import com.PolygonGamesStudio.UJourney.Adapter.ProfileAdapter;
import com.PolygonGamesStudio.UJourney.NavigationDrawer.DrawerItemClickListener;
import com.PolygonGamesStudio.UJourney.Service.CategoryService;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;

public class MainMenuActivity extends Activity{

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private ListView mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        BaseAdapter drawer_adapter = new DrawerAdapter(MainMenuActivity.this);

        View header = getLayoutInflater().inflate(R.layout.drawer_list_header, null);
        mDrawerList.addHeaderView(header);

        mDrawerList.setAdapter(drawer_adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener(this));
        mTitle = mDrawerTitle = getTitle();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
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

        ListView lvMainMenu = (ListView) findViewById(R.id.listViewMainMenu);
        MainMenuAdapter adapter = new MainMenuAdapter(MainMenuActivity.this);


        lvMainMenu.setAdapter(adapter);

        lvMainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:  // 0 - Случайно
                        break;
                    case 1:  // 1 - Категории
                        Intent service = new Intent(MainMenuActivity.this, CategoryService.class);
                        startService(service);

                        Intent intent = new Intent(MainMenuActivity.this, CategoryListActivity.class);
                        startActivity(intent);
                        break;
                    case 2:  // 2 - Предложения
                        break;
                }
            }
        });
    }

}
