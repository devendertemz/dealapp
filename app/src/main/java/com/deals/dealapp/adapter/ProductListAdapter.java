package com.deals.dealapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.R;
import com.deals.dealapp.model.ProductList;

import java.util.ArrayList;

public class ProductListAdapter  extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private ArrayList<ProductList> mValues;
    private Context mContext;
    protected ProductListAdapter.ItemListener mListener;

    public ProductListAdapter(ArrayList<ProductList> mValues, Context mContext, ProductListAdapter.ItemListener mListener) {
        this.mValues = mValues;
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public ProductListAdapter(ArrayList<ProductList> mValues, Context mContext) {
        this.mValues = mValues;
        this.mContext = mContext;

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private ImageView P_Iamge;
        private RelativeLayout relativeLayout;

        private TextView P_Name,P_Finalprice,P_OfferPrice,P_Taxdeatils;
        private ProductList item;

        public ViewHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this);
            P_Name = (TextView) v.findViewById(R.id.p_Name);
            P_Finalprice = (TextView) v.findViewById(R.id.p_price);
            P_OfferPrice = (TextView) v.findViewById(R.id.p_Offer);
            P_Taxdeatils = (TextView) v.findViewById(R.id.tax_details);


            P_Iamge = (ImageView) v.findViewById(R.id.productimage);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.productrelativeLayout);
        }
        public void setData(ProductList item) {
            this.item = item;
            P_Name.setText(item.P_Name);
            P_Finalprice.setText(item.P_Finalprice);
            P_OfferPrice.setText(item.P_OfferPrice);
            P_Taxdeatils.setText(item.P_Taxdeatils);
            P_Iamge.setImageResource(item.P_Iamge);
           // relativeLayout.setBackgroundColor(Color.parseColor(item.));
        }
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);

            }
        }
    }
    @NonNull
    @Override
    public  ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.thirdlist_recycler_item, parent, false);
        return new ProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public interface ItemListener {
        void onItemClick(ProductList item);
    }

    }
