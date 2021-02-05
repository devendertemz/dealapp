package com.deals.dealapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.deals.dealapp.R;

public class HomeFragment extends Fragment {

    LinearLayout Search_layout;

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
        Search_layout = (LinearLayout) view.findViewById(R.id.search_layout);


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

        return view;

    }
}