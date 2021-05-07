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
import com.deals.dealapp.ModelResponse.Secondcategory_itemlist;
import com.deals.dealapp.R;
import com.deals.dealapp.model.GridHomeModel;
import com.deals.dealapp.model.JewalleryModel;
import com.deals.dealapp.model.ProductList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Jewallery_Adapterr extends RecyclerView.Adapter<Jewallery_Adapterr.Jewallery_AdapterrVH> {

    private List<JewalleryModel> userResponseList;
    private Context context;
    private Jewallery_Adapterr.ClickedItem clickedItem;

    public Jewallery_Adapterr(Jewallery_Adapterr.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }
    public void setData(List<JewalleryModel> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Jewallery_AdapterrVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new Jewallery_Adapterr.Jewallery_AdapterrVH(LayoutInflater.from(context).inflate(R.layout.jewellery_adapter_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Jewallery_AdapterrVH holder, int position) {

        JewalleryModel userResponse = userResponseList.get(position);
        // Log.e("image",userResponse.getImage());
        String url = "http://api.ourprive.com/" + userResponse.getImage();


        Picasso.get().load(url).into(holder.jewellery_offer_image);
        Log.e("image", url);

        holder.jewellery_offer_title.setText(userResponse.getCategoryName());


        holder.jewellery_adapter_layoutclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });
    }

    public interface ClickedItem {
        public void ClickedUser(JewalleryModel userResponse);
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class Jewallery_AdapterrVH extends RecyclerView.ViewHolder {

        ImageView jewellery_offer_image;
        TextView jewellery_offer_title;
        LinearLayout jewellery_adapter_layoutclick;


        public Jewallery_AdapterrVH(@NonNull View itemView) {
            super(itemView);
            jewellery_offer_title = (TextView) itemView.findViewById(R.id.jewellery_offer_title);
            jewellery_offer_image = (ImageView) itemView.findViewById(R.id.jewellery_offer_image);
            jewellery_adapter_layoutclick=itemView.findViewById(R.id.jewellery_adapter_layoutclick);
        }
    }
}
/*



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
*/
