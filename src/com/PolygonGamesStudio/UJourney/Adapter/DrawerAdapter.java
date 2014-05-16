package com.PolygonGamesStudio.UJourney.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.PolygonGamesStudio.UJourney.Holder.ViewHolder;
import com.PolygonGamesStudio.UJourney.R;

public class DrawerAdapter extends BaseAdapter {
    Context ctx;
    String[] names = {
            "История",
            "Избранное",
            "Настройки",
            "Выход",
    };

    int[] icons = {
            R.drawable.btn_history_normal,
            R.drawable.btn_favourite_normal,
            R.drawable.btn_settings_normal,
            R.drawable.btn_logout_normal
    };

    public DrawerAdapter(Context ctx) {
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.drawer_list_item, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.headerTextView);
            holder.icon = (ImageView) convertView.findViewById(R.id.headerImageView);
            holder.arrow = (ImageView) convertView.findViewById(R.id.drawerArrow);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(names[position]);
        holder.icon.setImageResource(icons[position]);
        holder.arrow.setImageResource(R.drawable.btn_arrow_right_normal);

        return convertView;


    }
}
