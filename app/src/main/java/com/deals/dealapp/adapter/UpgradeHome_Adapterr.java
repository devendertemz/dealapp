package com.deals.dealapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.R;
import com.deals.dealapp.model.JewalleryModel;
import com.deals.dealapp.model.UpgradeHomeModel;
import com.squareup.picasso.Picasso;


import java.util.List;

public class UpgradeHome_Adapterr extends RecyclerView.Adapter<UpgradeHome_Adapterr.UpgradeHome_AdapterrVH> {

    private List<UpgradeHomeModel> userResponseList;
    private Context context;
    private UpgradeHome_Adapterr.ClickedItem clickedItem;

    public UpgradeHome_Adapterr(UpgradeHome_Adapterr.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<UpgradeHomeModel> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UpgradeHome_AdapterrVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UpgradeHome_Adapterr.UpgradeHome_AdapterrVH(LayoutInflater.from(context).inflate(R.layout.upgradehome_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UpgradeHome_AdapterrVH holder, int position) {
        UpgradeHomeModel userResponse = userResponseList.get(position);
        // Log.e("image",userResponse.getImage());
        String url = "http://api.ourprive.com/" + userResponse.getImage();

        Picasso.get().load(url).into(holder.jewellery_offer_image);
        Log.e("image", url);

        holder.jewellery_offer_title.setText(userResponse.getCategoryname());

        holder.Upgradehomeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });
    }

    public interface ClickedItem {
        public void ClickedUser(UpgradeHomeModel userResponse);
    }

    @Override
    public int getItemCount() {
      return   userResponseList.size();
    }


    public class UpgradeHome_AdapterrVH extends RecyclerView.ViewHolder {
        ImageView jewellery_offer_image;
        TextView jewellery_offer_title;
        LinearLayout Upgradehomeclick;

        public UpgradeHome_AdapterrVH(@NonNull View itemView) {
            super(itemView);
            jewellery_offer_title = (TextView) itemView.findViewById(R.id.jewellery_offer_title);
            jewellery_offer_image = (ImageView) itemView.findViewById(R.id.jewellery_offer_image);
            Upgradehomeclick=itemView.findViewById(R.id.Upgradehomeclick);
        }
    }
}

/*
    private ArrayList<JewalleryModel> mValues;
    private Context mContext;
    protected ProductListAdapter.ItemListener mListener;

    public UpgradeHome_Adapterr(ArrayList<JewalleryModel> mValues, Context mContext, ProductListAdapter.ItemListener mListener) {
        this.mValues = mValues;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public UpgradeHome_Adapterr(ArrayList<JewalleryModel> mValues, Context mContext) {
        this.mValues = mValues;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upgradehome_adapter_layout, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


       *//* holder.jewellery_offer_title.setText(mValues.get(position).getName());
        holder.jewellery_offer_image.setImageResource(mValues.get(position).getImage());*//*
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
    }*/