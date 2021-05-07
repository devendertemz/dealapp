package com.deals.dealapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.ModelResponse.Get_trending_categories_Response;
import com.deals.dealapp.R;
import com.deals.dealapp.model.Item;
import com.deals.dealapp.model.JewalleryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Get_trending_categories_Adapter  extends RecyclerView.Adapter<Get_trending_categories_Adapter.CategoryHomeAdapterHV> {

    private List<Get_trending_categories_Response> userResponseList;
    private Context context;
    private Get_trending_categories_Adapter.ClickedItem clickedItem;

    public Get_trending_categories_Adapter(Get_trending_categories_Adapter.ClickedItem clickedItem) {

        this.clickedItem = clickedItem;
    }
    public void setData(List<Get_trending_categories_Response> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Get_trending_categories_Adapter.CategoryHomeAdapterHV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new Get_trending_categories_Adapter.CategoryHomeAdapterHV(LayoutInflater.from(context).inflate(R.layout.gridview_layout_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Get_trending_categories_Adapter.CategoryHomeAdapterHV holder, int position) {

        Get_trending_categories_Response userResponse = userResponseList.get(position);
      //Log.e("image",userResponse.getImage());
        String url = "http://api.ourprive.com/" + userResponse.getImage();

        Picasso.get().load(url).into(holder.imageView);
        Log.e("image", url);

        holder.textView.setText(userResponse.getCategoryName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });


    }

    public interface ClickedItem {
        public void ClickedUser(Get_trending_categories_Response userResponse);
    }


    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class CategoryHomeAdapterHV extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private LinearLayout linearLayout;
        private Item item;
        public CategoryHomeAdapterHV(@NonNull View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.offer_titlee);
            imageView = (ImageView) v.findViewById(R.id.offer_imagee);
            linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
        }
    }
}