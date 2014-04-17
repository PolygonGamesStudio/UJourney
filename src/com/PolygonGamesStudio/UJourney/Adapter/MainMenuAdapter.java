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

public class MainMenuAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    String[] names = { "Случайно","Категории", "Предложения"};
//  TODO: обтекание текста
    String[] descriptions = { "Выбор случайного \n маршрута ...",
            "Выбор маршрута \n по категориям ...",
            "Рекомендованные \n маршруты ..."
            };
    int[] icons = { R.drawable.menu_random, R.drawable.menu_category, R.drawable.menu_favorite};

    public MainMenuAdapter(Context ctx) {
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
            convertView = lInflater.from(ctx).inflate(R.layout.main_menu_list_item, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.headerTextView);
            holder.descriptions = (TextView) convertView.findViewById(R.id.descriptionTextView);
            holder.icon = (ImageView) convertView.findViewById(R.id.headerImageView);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(names[position]);
        holder.descriptions.setText(descriptions[position]);
        holder.icon.setImageResource(icons[position]);


        return convertView;


    }
}
