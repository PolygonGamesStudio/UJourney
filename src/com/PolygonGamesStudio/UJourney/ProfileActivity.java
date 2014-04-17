package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.Adapter.ProfileAdapter;

public class ProfileActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ListView lvPlaces = (ListView) findViewById(R.id.PlacesListView);
        ProfileAdapter adapter = new ProfileAdapter(ProfileActivity.this);
        lvPlaces.setAdapter(adapter);

    }
}