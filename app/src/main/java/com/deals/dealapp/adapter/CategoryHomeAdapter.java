package com.deals.dealapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.R;
import com.deals.dealapp.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeAdapterHV> {

    private List<CategoryListModel> userResponseList;
    private Context context;
    private CategoryHomeAdapter.ClickedItem clickedItem;

    public CategoryHomeAdapter(CategoryHomeAdapter.ClickedItem clickedItem) {

        this.clickedItem = clickedItem;
    }
    public void setData(List<CategoryListModel> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryHomeAdapterHV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CategoryHomeAdapter.CategoryHomeAdapterHV(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeAdapterHV holder, int position) {

        CategoryListModel userResponse = userResponseList.get(position);
        // Log.e("image",userResponse.getImage());
        String url = "http://api.ourprive.com/" + userResponse.getIcon();

        Picasso.get().load(url).into(holder.imageView);
       // Log.e("image", url);

        holder.textView.setText(userResponse.getCategoryname());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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

    public class CategoryHomeAdapterHV extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private Item item;
        public CategoryHomeAdapterHV(@NonNull View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
        }
    }
}