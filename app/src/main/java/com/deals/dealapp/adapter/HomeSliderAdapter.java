package com.deals.dealapp.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.deals.dealapp.ModelResponse.HomeSliderList;
import com.deals.dealapp.R;
import com.deals.dealapp.model.ProductList;

import java.util.ArrayList;

public class HomeSliderAdapter extends BaseAdapter {
    private Context mContext;
    LayoutInflater inflter;
    private ArrayList<HomeSliderList> mValues;
    public HomeSliderAdapter(Context mContext, ArrayList<HomeSliderList> mValues) {
        this.mContext = mContext;
        this.mValues = mValues;
        inflter = (LayoutInflater.from(mContext));

    }



    @Override
    public int getCount() {
        return mValues.size();
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
        view = inflter.inflate(R.layout.sliderlayout_adapter, null);
        TextView Names = (TextView) view.findViewById(R.id.p_Name);
        TextView offer = (TextView) view.findViewById(R.id.offer);
        TextView online = (TextView) view.findViewById(R.id.online);

        ImageView image=view.findViewById(R.id.image);

        Names.setText(mValues.get(position).getName());

        offer.setText(mValues.get(position).getOffer());
        online.setText(mValues.get(position).getProdcut());
      //  image.

        // coinSymbol.setText(coinSymbols[position]);


        return view;
    }
}
