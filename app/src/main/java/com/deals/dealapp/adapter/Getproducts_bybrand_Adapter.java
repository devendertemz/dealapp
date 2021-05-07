package com.deals.dealapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filter;
import com.deals.dealapp.Interface.ItemClickListener;
import com.deals.dealapp.ModelResponse.Getproducts_bybrandResp;
import com.deals.dealapp.R;
import com.deals.dealapp.model.ProductList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Getproducts_bybrand_Adapter extends RecyclerView.Adapter<Getproducts_bybrand_Adapter.MyViewHolder> implements Filterable {

    ArrayList<Getproducts_bybrandResp> dataList;
    ItemClickListener itemClickListener;

    List<Getproducts_bybrandResp> mDataFiltered ;

    Context context;


    // String usertype = SharedPrefManager.getInstans(context).getLogintype();
    //String username = SharedPrefManager.getInstans(this).getUsername();

    // public onItemClick onItemClick;

    public Getproducts_bybrand_Adapter(ArrayList<Getproducts_bybrandResp> dataList, ItemClickListener itemClickListene) {


        this.dataList = dataList;
        this.itemClickListener = itemClickListene;

        this.mDataFiltered = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.thirdlist_recycler_item, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Log.e("prodddd",mDataFiltered.get(i).getProductType());


        myViewHolder.P_Name.setText(mDataFiltered.get(i).getProductName());
        myViewHolder.P_Finalprice.setText("Rs."+mDataFiltered.get(i).getActualAmount());

        myViewHolder.P_OfferPrice.setText("Rs."+mDataFiltered.get(i).getPrice()+"("+mDataFiltered.get(i).getDiscount()+"% off)");
        myViewHolder.P_Taxdeatils.setText(" Price inclusive of all taxes");

        String url = "http://api.ourprive.com/" + mDataFiltered.get(i).getImages();

        Picasso.get().load(url).into(myViewHolder.P_Iamge);
        //    myViewHolder.Query.setText(mDataFiltered.get(i).getEnquiry_details());
        /*if (usertype.equals("MasterAdmin"))
        {

            myViewHolder.button.setVisibility(View.VISIBLE);
        }*/

    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    mDataFiltered = dataList ;
                }
                else {
                    List<Getproducts_bybrandResp> lstFiltered = new ArrayList<>();
                    for (Getproducts_bybrandResp row : dataList) {
                        Log.e("prod",row.getProductType());


                        if (row.getProductType().toLowerCase().contains(Key.toLowerCase())){

                            lstFiltered.add(row);
                        }

                    }

                    mDataFiltered = lstFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (List<Getproducts_bybrandResp>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private ImageView P_Iamge;
        private LinearLayout relativeLayout;

        private TextView P_Name,P_Finalprice,P_OfferPrice,P_Taxdeatils;

        public MyViewHolder(@NonNull View v) {
            super(v);
            P_Name = (TextView) v.findViewById(R.id.p_Name);
            P_Finalprice = (TextView) v.findViewById(R.id.p_price);
            P_OfferPrice = (TextView) v.findViewById(R.id.p_Offer);
            P_Taxdeatils = (TextView) v.findViewById(R.id.tax_details);


            P_Iamge = (ImageView) v.findViewById(R.id.productimage);
            relativeLayout = (LinearLayout) v.findViewById(R.id.productrelativeLayout);
            relativeLayout.setOnClickListener(this);

            /*layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/



        }

        @Override
        public void onClick(View v) {


            itemClickListener.onClick(v,getAdapterPosition(),false);

        }
    }

 /*   public interface onItemClick {
        public void onBtnClick(String taskId, String status);


    }*/
}