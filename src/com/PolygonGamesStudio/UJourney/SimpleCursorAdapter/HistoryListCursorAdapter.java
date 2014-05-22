package com.PolygonGamesStudio.UJourney.SimpleCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.PolygonGamesStudio.UJourney.R;
import com.squareup.picasso.Picasso;

public class HistoryListCursorAdapter extends SimpleCursorAdapter{

    private Context mContext;
    private int layoutResourceId;
    private LayoutInflater inflater;

    public HistoryListCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.mContext = context;
        this.layoutResourceId = layout;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View row = inflater.inflate(layoutResourceId, parent, false);
        row.setTag(new HistoryHolder(row));
        return row;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        super.bindView(view, context, cursor);  // FIXME: possible here

//        PROJECTION =  {"_id", "title", "visit", "picture"}

        final HistoryHolder holder = (HistoryHolder) view.getTag();

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String historyItemId = holder.id.getText().toString();
                Toast.makeText(mContext, "Accept initiated. HistItem: " + historyItemId, Toast.LENGTH_SHORT).show();
                context.getContentResolver().delete(CacheContentProvider.HISTORY_CONTENT_URI, "_id =" + historyItemId, null);
            }
        });
        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String historyItemId = holder.id.getText().toString();
                Toast.makeText(mContext, "Decline initiated. From: " + historyItemId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        int wightImage = 200;
        int heightImage = 200;

        Picasso.with(mContext).load(value).resize(wightImage, heightImage).transform(PicassoHelper.getTransform()).into(v);
    }

    private class HistoryHolder {
        TextView id;
        ImageView image;
        TextView title;
        TextView visitDate;
        ImageButton btnAccept;
        Button btnDecline;

        private HistoryHolder(View v) {
            this.id = (TextView)v.findViewById(R.id.histElId);
            this.image = (ImageView)v.findViewById(R.id.histElPicture);
            this.title = (TextView)v.findViewById(R.id.histElTitle);
            this.visitDate = (TextView)v.findViewById(R.id.histElVisitTime);
            this.btnAccept = (ImageButton)v.findViewById(R.id.btnAccept);
            this.btnDecline = (Button)v.findViewById(R.id.btnDecline);
        }
    }
}

