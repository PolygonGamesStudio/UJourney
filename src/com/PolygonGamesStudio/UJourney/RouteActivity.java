package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;
import com.PolygonGamesStudio.UJourney.Service.RouteService;
import com.PolygonGamesStudio.UJourney.SimpleCursorAdapter.ProfileHistorySimpleCursorAdapter;
import com.PolygonGamesStudio.UJourney.SimpleCursorAdapter.RouteSimpleCursorAdapter;

public class RouteActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    SimpleCursorAdapter scAdapter;
    private static final String[] PROJECTION =  new  String[]{"_id", "text"};
    private static final int[] viewID =  new  int[]{R.id.nodeID, R.id.node_text};

    public static int currentPosition = 1;
    SimpleCursorAdapter scAdapter1;
    Cursor cursor;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route);

        ListView lvNodes = (ListView) findViewById(R.id.route_nodes_ListView);
        View header = getLayoutInflater().inflate(R.layout.route_list_header, null);
        lvNodes.addHeaderView(header);


        getLoaderManager().initLoader(0, null, this);
    }

//    public static void createSCAdapter(){
//        SimpleCursorAdapter scAdapter1 = new RouteSimpleCursorAdapter(this, R.layout.route_list_item, null, PROJECTION, viewID, 0);
//        ListView lvData = (ListView) findViewById(R.id.route_nodes_ListView);
//        lvData.setAdapter(scAdapter1);
//    }

    @Override//+
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(RouteActivity.this, CacheContentProvider.NODE_CONTENT_URI, PROJECTION, null, null, null);
    }

    @Override//+
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter = new RouteSimpleCursorAdapter(this, R.layout.route_list_item, data, PROJECTION, viewID, 0);
        ListView lvData = (ListView) findViewById(R.id.route_nodes_ListView);
        lvData.setAdapter(scAdapter);

        registerForContextMenu(lvData);

        scAdapter.swapCursor(data);
        cursor = data;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }
}
