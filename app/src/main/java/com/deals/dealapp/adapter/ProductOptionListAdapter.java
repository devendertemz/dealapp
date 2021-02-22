package com.deals.dealapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.R;
import com.deals.dealapp.model.Item;
import com.deals.dealapp.model.ProductListOptions;

import java.util.ArrayList;

public class ProductOptionListAdapter extends RecyclerView.Adapter<ProductOptionListAdapter.ViewHolder> {

    private ArrayList<ProductListOptions> mValues;
    private Context mContext;
    protected ProductOptionListAdapter.ItemListener mListener;

    public ProductOptionListAdapter(ArrayList<ProductListOptions> mValues, Context mContext, ProductOptionListAdapter.ItemListener mListener) {
        this.mValues = mValues;
        this.mContext = mContext;
        this.mListener = mListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private ProductListOptions item;
        private TextView optionn;

        public ViewHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this);
            optionn = (TextView) v.findViewById(R.id.Listname);

        }
        public void setData(ProductListOptions item) {
            this.item = item;
            optionn.setText(item.Fliteroption);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(item);


        }

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.optiondata_thirdlistadapter, parent, false);
        return new ProductOptionListAdapter.ViewHolder(view);
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
        void onItemClick(ProductListOptions item);
    }

}
