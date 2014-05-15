package com.PolygonGamesStudio.UJourney.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.PolygonGamesStudio.UJourney.Helper.ImageHelper;
import com.PolygonGamesStudio.UJourney.Helper.PicassoHelper;
import com.PolygonGamesStudio.UJourney.Holder.ViewHolder;
import com.PolygonGamesStudio.UJourney.R;
import com.squareup.picasso.Picasso;

public class ProfileAdapter extends BaseAdapter {
    Context ctx;
    String[] names = { "Старбакс","Макдональдс", "Петрович"};
    String[] descriptions = { "Час назад",
            "6 марта 2014",
            "15 июля 2013"
    };
//    int[] icons = { R.drawable.menu_random, R.drawable.menu_category, R.drawable.menu_favorite};

    public ProfileAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {

        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.profile_list_item, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.headerTextView);
            holder.descriptions = (TextView) convertView.findViewById(R.id.descriptionTextView);
//            ImageHelper.getRoundedCornerBitmap()
//            TODO: кругляшки Битмап передавать в ImageView
//            holder.icon = (ImageView) convertView.findViewById(R.id.headerImageView);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(names[position]);
        holder.descriptions.setText(descriptions[position]);
//        holder.icon.setImageResource(icons[position]);


        return convertView;


    }
}
