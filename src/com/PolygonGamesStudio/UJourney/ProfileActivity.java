package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.Adapter.ProfileAdapter;

public class ProfileActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ListView lvPlaces = (ListView) findViewById(R.id.PlacesListView);
        ProfileAdapter adapter = new ProfileAdapter(ProfileActivity.this);
        View header = getLayoutInflater().inflate(R.layout.profile_list_header, null);
        lvPlaces.addHeaderView(header);
        lvPlaces.setAdapter(adapter);

    }
}