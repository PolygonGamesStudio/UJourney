package com.PolygonGamesStudio.UJourney.SimpleCursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.PolygonGamesStudio.UJourney.ContentProvider.CacheContentProvider;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.PolygonGamesStudio.UJourney.Holder.ViewHolder;
import com.PolygonGamesStudio.UJourney.R;
import com.PolygonGamesStudio.UJourney.RouteActivity;
import com.squareup.picasso.Picasso;

public class RouteSimpleCursorAdapter extends SimpleCursorAdapter {
    Context context_instance;
    Cursor mCursor;
    Context mContext;
    LayoutInflater mLayoutInflater;
    int mLayout;
    int mNameIndex;
    int mtextIndex;

    public RouteSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.mLayout = layout;
        this.context_instance = context;
        this.mCursor = c;
        this.mContext = context;
        this.mNameIndex = mCursor.getColumnIndex("_id");
        this.mtextIndex = mCursor.getColumnIndex("text");
        Log.e("log", DatabaseUtils.dumpCursorToString(c));
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mCursor.moveToPosition(position)) {
            RouteHolder viewHolder;


            if (convertView == null) {
                convertView = mLayoutInflater.inflate(mLayout, null);

                viewHolder = new RouteHolder(convertView);
                viewHolder.text = (TextView) convertView.findViewById(R.id.node_text);
//                viewHolder.image = (ImageView) convertView.findViewById(R.id.contact_pic);
//                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);

                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (RouteHolder) convertView.getTag();
            }

            String name = mCursor.getString(mNameIndex);
            String text = mCursor.getString(mtextIndex);
            //String fb_id = mCursor.getString(mIdIndex);
            //Drawable drawable = LoadImageFromWebOperations("http://graph.facebook.com/"+fb_id+"/picture");
            //boolean isChecked = ((GlobalVars) mContext.getApplicationContext()).isFriendSelected(fb_id);
            viewHolder.text.setText(text);
            viewHolder.id.setText(name);
//            viewHolder.image.setImageDrawable(drawable);
//            viewHolder.checkBox.setTag(fb_id);
//            viewHolder.checkBox.setChecked(isChecked);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RouteActivity.currentPosition ++;
                    View currentView = ((ListView)v.getParent()).getChildAt(RouteActivity.currentPosition);
                    currentView.setVisibility(View.VISIBLE);
                    currentView.setEnabled(true);
                    v.setBackgroundColor(Color.rgb(8,196,99));
//                currentView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        RouteActivity.currentPosition ++;
//                        View currentView = ((ListView)v.getParent()).getChildAt(RouteActivity.currentPosition);
//                        currentView.setVisibility(View.VISIBLE);
//                        currentView.setOnClickListener();
//                    }
//                });
                    //setEnabled
                } catch (NullPointerException e){

                }

            }
        });
        if (position != 0){
            convertView.setVisibility(View.INVISIBLE);
            convertView.setEnabled(false);
        } else {

        }

        return convertView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View row = mLayoutInflater.inflate(mLayout, parent, false);
        row.setTag(new RouteHolder(row));
        return row;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor); // FIXME: possible here
        RouteHolder holder = (RouteHolder) view.getTag();

//        if (holder.nodeID != 0){
//            view.setVisibility(View.INVISIBLE);
//        }

// PROJECTION = {"_id", "title", "visit", "picture"}





//        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "Accept initiated", Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "Decline initiated", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private class RouteHolder extends ViewHolder{
        //int nodeID;
        public TextView id;
        public TextView text;

        private RouteHolder(View v/*, int nodeID*/) {
            this.id = (TextView)v.findViewById(R.id.nodeID);
            this.text = (TextView)v.findViewById(R.id.node_text);
            //this.nodeID = nodeID;
        }
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        int wightImage = 120;
        int heightImage = 120;

        Picasso.with(context_instance).load(value).resize(wightImage, heightImage).transform(PicassoHelper.getTransform()).into(v);
    }
}
