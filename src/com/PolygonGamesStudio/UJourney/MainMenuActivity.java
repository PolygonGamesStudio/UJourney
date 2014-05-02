package com.PolygonGamesStudio.UJourney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.Adapter.MainMenuAdapter;
import com.PolygonGamesStudio.UJourney.Service.CategoryService;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;

public class MainMenuActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        ListView lvMainMenu = (ListView) findViewById(R.id.listViewMainMenu);
        MainMenuAdapter adapter = new MainMenuAdapter(MainMenuActivity.this);
        lvMainMenu.setAdapter(adapter);

        lvMainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              0 - Случайно
//              1 - Категории
//              2 - Предложения
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        Intent service = new Intent(MainMenuActivity.this, CategoryService.class);
                        startService(service);

                        Intent intent = new Intent(MainMenuActivity.this, CategoryListActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent service_1 = new Intent(MainMenuActivity.this, HistoryService.class);
                        startService(service_1);

                        Intent intent_1 = new Intent(MainMenuActivity.this, HistoryListActivity.class);
                        startActivity(intent_1);
                        break;
                }
            }
        });


    }
}
