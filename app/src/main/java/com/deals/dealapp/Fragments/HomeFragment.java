package com.deals.dealapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.deals.dealapp.R;
import com.deals.dealapp.adapter.HomeAdapter;
import com.deals.dealapp.model.Item;

import java.util.ArrayList;

public class HomeFragment extends Fragment  implements HomeAdapter.ItemListener{

    LinearLayout Search_layout;
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;

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
        initView(view );



        return view;

    }

    private void initView(View view) {
        Search_layout = (LinearLayout) view.findViewById(R.id.search_layout);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        setRecyclerdata();
        Search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchFragment searchFragment = new SearchFragment();
                FragmentManager m = getFragmentManager();
                FragmentTransaction fragmentTransaction = m.beginTransaction();
                fragmentTransaction.replace(R.id.contentPanel, searchFragment);
                fragmentTransaction.commit();
            }
        });


    }

    public void setRecyclerdata()
    {
        arrayList = new ArrayList<>();


        arrayList.add(new Item("Apparel", R.drawable.apparel, "#E0E0E0"));
        arrayList.add(new Item("Car Wash", R.drawable.car_wash, "#E0E0E0"));
        arrayList.add(new Item("Beauty Parlour", R.drawable.beautyparlour, "#E0E0E0"));
        arrayList.add(new Item("Groceries ", R.drawable.groceries, "#E0E0E0"));
        arrayList.add(new Item(" Hotels", R.drawable.hotels, "#E0E0E0"));
        arrayList.add(new Item("Restaurants", R.drawable.hotels, "#E0E0E0"));

        HomeAdapter adapter = new HomeAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }
    @Override
    public void onItemClick(Item item) {

        if(item.text.equals("Apparel"))
        {

        }else if (item.text.equals("Car Wash"))
        {

        }
        else if (item.text.equals("Beauty Parlour"))
        {

        }
        else if (item.text.equals("Groceries"))
        {

        }
        else if (item.text.equals("Hotels"))
        {

        }
        else if (item.text.equals("Restaurants"))
        {

        }


        Toast.makeText(getContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }


}