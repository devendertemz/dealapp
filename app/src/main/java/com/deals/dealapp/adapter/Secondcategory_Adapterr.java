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
import com.squareup.picasso.Picasso;

import java.util.List;

public class Secondcategory_Adapterr extends RecyclerView.Adapter<Secondcategory_Adapterr.Secondcategory_AdapterrVH> {

    private List<Secondcategory_itemlist> userResponseList;
    private Context context;
    private Secondcategory_Adapterr.ClickedItem clickedItem;


    public Secondcategory_Adapterr(Secondcategory_Adapterr.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<Secondcategory_itemlist> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Secondcategory_AdapterrVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new Secondcategory_Adapterr.Secondcategory_AdapterrVH(LayoutInflater.from(context).inflate(R.layout.secondlist_rowitem, parent, false));

    }

    public interface ClickedItem {
        public void ClickedUser(Secondcategory_itemlist userResponse);


    }

    @Override
    public void onBindViewHolder(@NonNull Secondcategory_AdapterrVH holder, int position) {


        Secondcategory_itemlist userResponse = userResponseList.get(position);
        // Log.e("image",userResponse.getImage());
        String url = "http://api.ourprive.com/" + userResponse.getImage();

        Picasso.get().load(url).into(holder.imageView);
        Log.e("image", url);

        holder.textView.setText(userResponse.getSubcatename());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });

    }



    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class Secondcategory_AdapterrVH extends RecyclerView.ViewHolder {


        private ImageView imageView;
        private RelativeLayout relativeLayout;

        private TextView textView;

        private Secondcategory_itemlist item;
        public Secondcategory_AdapterrVH(@NonNull View v) {
            super(v);


            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
        }
    }
}