package com.deals.dealapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.ModelResponse.Secondcategory_itemlist;
import com.deals.dealapp.R;
import com.deals.dealapp.model.GridHomeModel;
import com.deals.dealapp.model.JewalleryModel;
import com.deals.dealapp.model.ProductList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Jewallery_Adapterr extends RecyclerView.Adapter<Jewallery_Adapterr.ViewHolder> {

    private ArrayList<JewalleryModel> mValues;
    private Context mContext;
    protected ProductListAdapter.ItemListener mListener;

    public Jewallery_Adapterr(ArrayList<JewalleryModel> mValues, Context mContext, ProductListAdapter.ItemListener mListener) {
        this.mValues = mValues;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public Jewallery_Adapterr(ArrayList<JewalleryModel> mValues, Context mContext) {
        this.mValues = mValues;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jewellery_adapter_layout, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.jewellery_offer_title.setText(mValues.get(position).getName());
        holder.jewellery_offer_image.setImageResource(mValues.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView jewellery_offer_image;
        TextView jewellery_offer_title;




        public ViewHolder(@NonNull View view) {
            super(view);
            jewellery_offer_title = (TextView) view.findViewById(R.id.jewellery_offer_title);
            jewellery_offer_image = (ImageView) view.findViewById(R.id.jewellery_offer_image);
        }
    }
}