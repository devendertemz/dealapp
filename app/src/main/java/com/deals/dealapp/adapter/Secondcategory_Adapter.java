package com.deals.dealapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deals.dealapp.R;
import com.deals.dealapp.model.Item;
import com.deals.dealapp.model.Secondcategory_itemlist;

import java.util.ArrayList;

public class Secondcategory_Adapter extends RecyclerView.Adapter<Secondcategory_Adapter.ViewHolder> {

private ArrayList<Secondcategory_itemlist> mValues;

private Context mContext;
protected ItemListener mListener;

public Secondcategory_Adapter(Context context, ArrayList<Secondcategory_itemlist> values, ItemListener itemListener) {
        mValues = values;
        mContext = context;
        mListener=itemListener;
        }

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private RelativeLayout relativeLayout;

    private TextView textView;

    private Secondcategory_itemlist item;

    public ViewHolder(View v) {
        super(v);
        v.setOnClickListener(this);
        textView = (TextView) v.findViewById(R.id.textView);
        imageView = (ImageView) v.findViewById(R.id.imageView);
        relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
    }

    public void setData(Secondcategory_itemlist item) {
        this.item = item;
        textView.setText(item.text);
        imageView.setImageResource(item.drawable);
        relativeLayout.setBackgroundColor(Color.parseColor(item.color));
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(item);
        }
    }
}

    @Override
    public Secondcategory_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.secondlist_rowitem, parent, false);
        return new Secondcategory_Adapter.ViewHolder(view);
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
    void onItemClick(Secondcategory_itemlist item);
}
}