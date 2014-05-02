package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.PolygonGamesStudio.UJourney.Service.CategoryService;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;
import com.squareup.picasso.Picasso;

public class LogInActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        Button loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }
}
