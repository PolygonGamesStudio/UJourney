package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        ImageView imageView = (ImageView) findViewById(R.id.LogoTextView);
        Picasso.with(LogInActivity.this).load("http://i.imgur.com/DvpvklR.png").into(imageView);

        Button anonymousButton = (Button) findViewById(R.id.anonymousButton);
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                FIXME: Тестовый сервис
                Intent intent = new Intent(LogInActivity.this, HistoryService.class);
                startService(intent);

                Intent intent1 = new Intent(LogInActivity.this, HistoryListActivity.class);
                startActivity(intent1);
            }
        });

        Button loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
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
