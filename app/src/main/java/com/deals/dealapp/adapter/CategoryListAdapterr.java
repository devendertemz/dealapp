package com.deals.dealapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.R;

import com.squareup.picasso.Picasso;


import java.util.List;

public class CategoryListAdapterr extends RecyclerView.Adapter<CategoryListAdapterr.CategoryListAdapterrVH> {


    private List<CategoryListModel> userResponseList;
    private Context context;
    private ClickedItem clickedItem;


    public CategoryListAdapterr(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<CategoryListModel> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryListAdapterrVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CategoryListAdapterr.CategoryListAdapterrVH(LayoutInflater.from(context).inflate(R.layout.store_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapterrVH holder, int position) {

        CategoryListModel userResponse = userResponseList.get(position);
        // Log.e("image",userResponse.getImage());
        String url = "http://api.ourprive.com/" + userResponse.getImage();

        Picasso.get().load(url).into(holder.imageView);
        Log.e("image", url);

        holder.Title.setText(userResponse.getCategoryname());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });

    }

    public interface ClickedItem {
        public void ClickedUser(CategoryListModel userResponse);
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class CategoryListAdapterrVH extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView Title;

        public CategoryListAdapterrVH(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.CategoryListModelImage);
            Title = itemView.findViewById(R.id.CategoryListModeltitle);

        }
    }
}
