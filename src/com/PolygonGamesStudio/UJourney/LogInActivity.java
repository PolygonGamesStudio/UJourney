package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.PolygonGamesStudio.UJourney.Service.TestService;

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

//                FIXME: Тестовый сервис
                Intent intent = new Intent(LogInActivity.this, TestService.class);
                startService(intent);

                Intent intent1 = new Intent(LogInActivity.this, ListPlacesActivity.class);
                startActivity(intent1);
            }
        });

        Button loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(LogInActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }
        });

        //        FIXME: Убрать нахуй ---
        Button profileButton = (Button) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(LogInActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        //        FIXME: Убрать нахуй ---
    }
}
