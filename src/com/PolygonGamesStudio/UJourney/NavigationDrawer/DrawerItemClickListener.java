package com.PolygonGamesStudio.UJourney.NavigationDrawer;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.HistoryListActivity;
import com.PolygonGamesStudio.UJourney.LogInActivity;
import com.PolygonGamesStudio.UJourney.PlaceActivity;
import com.PolygonGamesStudio.UJourney.ProfileActivity;
import com.PolygonGamesStudio.UJourney.Service.HistoryService;


public class DrawerItemClickListener implements ListView.OnItemClickListener {
    Activity parentActivity;

    public DrawerItemClickListener(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);
    }


    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        if (position == 0){  // 0 - профиль
            Intent intent = new Intent(parentActivity, ProfileActivity.class);
            parentActivity.startActivity(intent);
        }

        if (position == 1){  // 1 - История
            Intent history_service = new Intent(parentActivity, HistoryService.class);
            parentActivity.startService(history_service);

            Intent history_intent = new Intent(parentActivity, HistoryListActivity.class);
            parentActivity.startActivity(history_intent);
        }

        if (position == 2){  // 2 - Избранное
            Intent history_service = new Intent(parentActivity, HistoryService.class);
            parentActivity.startService(history_service);

            Intent place_intent = new Intent(parentActivity, PlaceActivity.class);
            parentActivity.startActivity(place_intent);
        }

        if (position == 4){  //  4 - выход
            Intent intent = new Intent(parentActivity, LogInActivity.class);
            parentActivity.startActivity(intent);
        }

    }
}