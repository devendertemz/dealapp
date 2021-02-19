package com.deals.dealapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.Activity.ThirdListStage;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.HomeAdapter;
import com.deals.dealapp.adapter.Secondcategory_Adapter;
import com.deals.dealapp.model.Item;
import com.deals.dealapp.model.Secondcategory_itemlist;

import java.util.ArrayList;

public class SecondListStage extends Fragment implements Secondcategory_Adapter.ItemListener{
    TextView categorgyname;
    private RecyclerView recyclerView;
    private ArrayList<Secondcategory_itemlist> arrayList;

    public SecondListStage() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_list_stage, container, false);
        initView(view);


        return view;
    }

    private void initView(View view) {

        categorgyname=view.findViewById(R.id.categorgyname);
        recyclerView=view.findViewById(R.id.recycler_secondcategory);
        try {

            Bundle mBundle = new Bundle();
            mBundle = getArguments();

            String value = mBundle.getString("item");
            if (value.trim().equals("Apparel"))
            {
                setApparelRecyclerdata();

            }
            Toast.makeText(getContext(), value + "", Toast.LENGTH_SHORT).show();
            categorgyname.setText(value);
        } catch (Exception e) {


        }

    }

    public void setApparelRecyclerdata()
    {


        arrayList = new ArrayList<>();
        arrayList.add(new Secondcategory_itemlist("MAX","#E0E0E0",   R.drawable.apparel));
        arrayList.add(new Secondcategory_itemlist("H&M",  "#E0E0E0",R.drawable.apparel));
        arrayList.add(new Secondcategory_itemlist("GAP",  "#E0E0E0",R.drawable.apparel));

        arrayList.add(new Secondcategory_itemlist("A|X",  "#E0E0E0",R.drawable.apparel));

        arrayList.add(new Secondcategory_itemlist("TRENDS",  "#E0E0E0",R.drawable.apparel));

        arrayList.add(new Secondcategory_itemlist("UCB",  "#E0E0E0",R.drawable.apparel));

        Secondcategory_Adapter adapter = new Secondcategory_Adapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(Secondcategory_itemlist item) {

        Intent in=new Intent(getContext(),ThirdListStage.class);


        if(item.text.trim().equals("MAX"))
        {
            in.putExtra( "item",item.text);


        }else if(item.text.trim().equals("H&M"))
        {
            in.putExtra( "item",item.text);


        }
        else if(item.text.trim().equals("GAP"))
        {
            in.putExtra( "item",item.text);


        }else if(item.text.trim().equals("A|X"))
        {
            in.putExtra( "item",item.text);


        }else if(item.text.trim().equals("TRENDS"))
        {
            in.putExtra( "item",item.text);


        }else if(item.text.trim().equals("UCB"))
        {
            in.putExtra( "item",item.text);


        }
        startActivity(in);


    }
}