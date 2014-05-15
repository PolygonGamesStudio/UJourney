package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.Adapter.ProfileAdapter;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Picasso.with(this).load("http://www.kopirkin.com.ua/wp-content/uploads/2014/04/547459_main-300x300.jpg")
                .transform(PicassoHelper.getTransform())
                .into((ImageView) findViewById(R.id.PhotoImageView));
        ListView lvPlaces = (ListView) findViewById(R.id.PlacesListView);
        ProfileAdapter adapter = new ProfileAdapter(ProfileActivity.this);
        View header = getLayoutInflater().inflate(R.layout.profile_list_header, null);
        lvPlaces.addHeaderView(header);
        lvPlaces.setAdapter(adapter);



    }
}