package com.deals.dealapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.deals.dealapp.R;
import com.deals.dealapp.model.GridHomeModel;

import java.util.ArrayList;

public class GridViewHomeCustomAdapter extends BaseAdapter {
    Context context;
    private ArrayList<GridHomeModel> arrayList;

    LayoutInflater inflter;
    public GridViewHomeCustomAdapter(Context applicationContext, ArrayList<GridHomeModel> arrayList) {
        this.context = applicationContext;
        this.arrayList = arrayList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = inflter.inflate(R.layout.gridview_layout_adapter, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.offer_image);
        TextView name = (TextView) view.findViewById(R.id.offer_title);


        // get the reference of ImageView
        icon.setImageResource(arrayList.get(position).getImage());
        name.setText(arrayList.get(position).getName()); // set logo images
// set logo images
        return view;

    }
}