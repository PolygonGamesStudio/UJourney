package com.PolygonGamesStudio.UJourney.SimpleCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.squareup.picasso.Picasso;

public class ProfileHistorySimpleCursorAdapter extends SimpleCursorAdapter {
    Context context_instance;

    public ProfileHistorySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context_instance = context;
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        int wightImage = 120;
        int heightImage = 120;

        Picasso.with(context_instance).load(value).resize(wightImage, heightImage).transform(PicassoHelper.getTransform()).into(v);
    }
}
