package com.deals.dealapp.Fragments;

import android.app.Notification;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.deals.dealapp.Activity.Edit_profile;
import com.deals.dealapp.Activity.NotificationsActivity;
import com.deals.dealapp.Activity.SearchActivity;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.HomeAdapter;
import com.deals.dealapp.model.Item;
import com.deals.dealapp.util;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment  implements HomeAdapter.ItemListener{

    LinearLayout Search_layout;
    CardView searchbar;

    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;
    CircleImageView profile_image;
    ImageView notification;

    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initView(view );



        return view;

    }

    private void initView(View view) {
       // Search_layout = (LinearLayout) view.findViewById(R.id.search_layout);
        util.blackiteamstatusbar(getActivity(), R.color.gray);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        profile_image= view.findViewById(R.id.profile_image);
        notification=view.findViewById(R.id.notification);
        searchbar=view.findViewById(R.id.searchbar);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(), SearchActivity.class);
                startActivity(in);


            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), NotificationsActivity.class);
                startActivity(in);

            }
        });
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),Edit_profile.class);
                startActivity(in);

            }
        });
        setRecyclerdata();
      /*  Search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchFragment searchFragment = new SearchFragment();
                FragmentManager m = getFragmentManager();
                FragmentTransaction fragmentTransaction = m.beginTransaction();
                fragmentTransaction.replace(R.id.contentPanel, searchFragment);
                fragmentTransaction.commit();
            }
        });*/


    }

    public void setRecyclerdata()
    {
        arrayList = new ArrayList<>();


        arrayList.add(new Item("Apparel", R.drawable.apparel, "#848484"));
        arrayList.add(new Item("Car Wash", R.drawable.car_wash, "#848484"));
        arrayList.add(new Item("Beauty Parlour", R.drawable.beautyparlour, "#848484"));
        arrayList.add(new Item("Groceries", R.drawable.groceries, "#848484"));
        arrayList.add(new Item("Hotels", R.drawable.hotels, "#848484"));
        arrayList.add(new Item("Restaurants", R.drawable.hotels, "#848484"));

        HomeAdapter adapter = new HomeAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

    }
    @Override
    public void onItemClick(Item item) {
        SecondListStage secondListStage = new SecondListStage ();
        Bundle args = new Bundle();


        if(item.text.trim().equals("Apparel"))
        {



            args.putString( "item",item.text);

          /*
            SearchFragment searchFragment = new SearchFragment();
            FragmentManager m = getFragmentManager();
            FragmentTransaction fragmentTransaction = m.beginTransaction();
            fragmentTransaction.replace(R.id.contentPanel, searchFragment);
            fragmentTransaction.commit();*/

        }else if (item.text.trim().equals("Car Wash"))
        {
            args.putString( "item",item.text);


        }
        else if (item.text.trim().equals("Beauty Parlour"))
        {
            args.putString( "item",item.text);
        }
        else if (item.text.trim().equals("Groceries"))
        {
            args.putString( "item",item.text);


        }
        else if (item.text.trim().equals("Hotels"))
        {

            args.putString( "item",item.text);

        }
        else if (item.text.trim().equals("Restaurants"))
        {
            args.putString( "item",item.text);


        }
        secondListStage.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.contentPanel, secondListStage).commit();


        //Toast.makeText(getContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }


}