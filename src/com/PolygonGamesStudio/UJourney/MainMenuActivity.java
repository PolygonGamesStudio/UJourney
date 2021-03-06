package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.PolygonGamesStudio.UJourney.Adapter.DrawerAdapter;
import com.PolygonGamesStudio.UJourney.Adapter.MainMenuAdapter;
import com.PolygonGamesStudio.UJourney.Adapter.ProfileAdapter;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.PolygonGamesStudio.UJourney.NavigationDrawer.DrawerItemClickListener;
import com.PolygonGamesStudio.UJourney.Service.CategoryService;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import com.PolygonGamesStudio.UJourney.Service.RouteService;
import com.squareup.picasso.Picasso;

public class MainMenuActivity extends Activity{

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private ListView mDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        initDrawer();

        ListView lvMainMenu = (ListView) findViewById(R.id.listViewMainMenu);
        MainMenuAdapter adapter = new MainMenuAdapter(MainMenuActivity.this);

        lvMainMenu.setAdapter(adapter);

        lvMainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:  // 0 - Случайно
                        Intent route_service = new Intent(MainMenuActivity.this, RouteService.class);
                        MainMenuActivity.this.startService(route_service);

                        Intent route_intent = new Intent(MainMenuActivity.this, RouteActivity.class);
                        MainMenuActivity.this.startActivity(route_intent);
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

    private void initDrawer(){
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        BaseAdapter drawer_adapter = new DrawerAdapter(MainMenuActivity.this);
        View header = getLayoutInflater().inflate(R.layout.drawer_list_header, null);
        ImageView v = (ImageView) header.findViewById(R.id.headerImageView);
        Picasso.with(MainMenuActivity.this).load(R.drawable.avatar_logout).transform(PicassoHelper.getTransform()).into(v);
        mDrawerList.addHeaderView(header);

        Cursor c = getContentResolver().query(CacheContentProvider.USER_CONTENT_URI, null, null, null, null);
        if (c.moveToFirst()) {

            TextView text_user = (TextView) header.findViewById(R.id.headerTextView);
            String name_user = c.getString(c.getColumnIndex("name"));
            text_user.setText(name_user);

            ImageView v_user = (ImageView) header.findViewById(R.id.headerImageView);
            Picasso.with(MainMenuActivity.this).load(c.getString(c.getColumnIndex("picture"))).transform(PicassoHelper.getTransform()).into(v_user);

        }

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
