package com.PolygonGamesStudio.UJourney.NavigationDrawer;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.HistoryListActivity;
import com.PolygonGamesStudio.UJourney.LogInActivity;
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
            Intent service_history = new Intent(parentActivity, HistoryService.class);
            parentActivity.startService(service_history);

            Intent intent_history = new Intent(parentActivity, HistoryListActivity.class);
            parentActivity.startActivity(intent_history);
        }

        if (position == 4){  //  4 - выход
            Intent intent = new Intent(parentActivity, LogInActivity.class);
            parentActivity.startActivity(intent);
        }

    }
}