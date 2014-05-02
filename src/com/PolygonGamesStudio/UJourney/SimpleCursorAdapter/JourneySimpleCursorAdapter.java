package com.PolygonGamesStudio.UJourney.SimpleCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
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

        Picasso.with(context_instance).load(value).resize(wightImage, heightImage).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());

                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                if (squaredBitmap != source) {
                    source.recycle();
                }

                Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);

                float r = size/2f;
                canvas.drawCircle(r, r, r, paint);

                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "circle";
            }
        }).into(v);
    }
}
