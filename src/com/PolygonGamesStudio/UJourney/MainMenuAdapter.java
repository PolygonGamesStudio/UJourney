package com.PolygonGamesStudio.UJourney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainMenuAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    String[] names = { "Случайно","Категории", "Предложения"};

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
            convertView = lInflater.from(ctx).inflate(R.layout.element_list_main_menu, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.headerTextView);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(names[position]);

        return convertView;


    }
}
