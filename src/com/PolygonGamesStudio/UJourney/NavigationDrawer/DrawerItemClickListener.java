package com.PolygonGamesStudio.UJourney.NavigationDrawer;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.PolygonGamesStudio.UJourney.HistoryListActivity;
import com.PolygonGamesStudio.UJourney.LogInActivity;
import com.PolygonGamesStudio.UJourney.ProfileActivity;

import java.util.zip.Inflater;

/**
 * Created by root on 4/26/14.
 */
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
        if (position == 0){
            Intent intent = new Intent(parentActivity, ProfileActivity.class);
            parentActivity.startActivity(intent);
        }

        if (position == 1){
            Intent intent = new Intent(parentActivity, HistoryListActivity.class);
            parentActivity.startActivity(intent);
        }

        if (position == 4){
            Intent intent = new Intent(parentActivity, LogInActivity.class);
            parentActivity.startActivity(intent);
        }



        // Create a new fragment and specify the planet to show based on position
//        Fragment fragment = new PlanetFragment();
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);
//
//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.content_frame, fragment)
//                .commit();
//
//        // Highlight the selected item, update the title, and close the drawer
//        mDrawerList.setItemChecked(position, true);
//        setTitle(mPlanetTitles[position]);
//        mDrawerLayout.closeDrawer(mDrawerList);
    }
}