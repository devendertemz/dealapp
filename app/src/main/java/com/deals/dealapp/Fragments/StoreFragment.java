package com.deals.dealapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deals.dealapp.Activity.SearchActivity;
import com.deals.dealapp.R;

public class StoreFragment extends Fragment {
    TextView searchtext;


    public StoreFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_store, container, false);
        initView(view );



        return view;
    }

    private void initView(View view) {
        searchtext=view.findViewById(R.id.searchtext);
        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), SearchActivity.class);
                startActivity(in);

            }
        });

    }
}