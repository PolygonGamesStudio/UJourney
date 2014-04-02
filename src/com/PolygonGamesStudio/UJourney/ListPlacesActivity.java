package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.Adapter.MainMenuAdapter;

public class ListPlacesActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        ListView lvMainMenu = (ListView) findViewById(R.id.listViewMainMenu);
        MainMenuAdapter adapter = new MainMenuAdapter(ListPlacesActivity.this);
        lvMainMenu.setAdapter(adapter);


    }
}
