package com.deals.dealapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.Interface.CategoryListClickListner;

import com.deals.dealapp.R;

import com.deals.dealapp.model.CategoryListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    private ArrayList<CategoryListModel> mValues;
    private Context mContext;
    private CategoryListClickListner categoryListClickListner;

    public CategoryListAdapter(ArrayList<CategoryListModel> mValues, Context mContext, CategoryListClickListner categoryListClickListner) {

        this.mValues = mValues;
        this.mContext = mContext;
        this.categoryListClickListner = categoryListClickListner;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_adapter_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        Picasso.get().load(mValues.get(position).getImage()).into(holder.imageView);
        holder.Title.setText(mValues.get(position).getCategoryname());


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView Title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.CategoryListModelImage);
            Title = itemView.findViewById(R.id.CategoryListModeltitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryListClickListner.onCategoryListClickListener(getAdapterPosition());
                }
            });

        }
    }
}
