package com.deals.dealapp.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.deals.dealapp.ModelResponse.HomeSliderList;
import com.deals.dealapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class SlidingImageAdapter extends PagerAdapter {
    private ArrayList<HomeSliderList> Images;
    private LayoutInflater layoutInflater;
    private Context context;

    public SlidingImageAdapter(ArrayList<HomeSliderList> images, Context context) {
        this.Images = images;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view =layoutInflater.inflate(R.layout.sliderlayout_adapter,container,false);

        assert view != null;


        TextView Names = (TextView) view.findViewById(R.id.p_Name);
        TextView offer = (TextView) view.findViewById(R.id.offer);
        TextView online = (TextView) view.findViewById(R.id.online);

        Names.setText(Images.get(position).getName());
        ImageView image=view.findViewById(R.id.image);

        offer.setText(Images.get(position).getOffer());
        online.setText(Images.get(position).getProdcut());
        image.setImageResource(Images.get(position).getImage());

        //Picasso.get().load(Images.get(position).getImageUrl()).into(imageView);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Nullable
    @Override

    public Parcelable saveState() {
        return null;
    }
}
