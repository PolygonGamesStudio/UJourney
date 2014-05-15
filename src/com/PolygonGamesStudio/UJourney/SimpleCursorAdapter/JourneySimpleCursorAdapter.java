package com.PolygonGamesStudio.UJourney.SimpleCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.squareup.picasso.Transformation;
import com.squareup.picasso.Picasso;

/**
 * Created by user1 on 4/26/14.
 */
public class JourneySimpleCursorAdapter extends SimpleCursorAdapter{

    Context context_instance;

    public JourneySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context_instance = context;
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        int wightImage = 200;
        int heightImage = 200;

        Picasso.with(context_instance).load(value).resize(wightImage, heightImage).transform(PicassoHelper.getTransform()).into(v);
    }
}
