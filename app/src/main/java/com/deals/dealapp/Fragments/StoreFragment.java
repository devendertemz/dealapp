package com.deals.dealapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deals.dealapp.Activity.SearchActivity;
import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.CategoryListAdapterr;
import com.deals.dealapp.databasee.Rtrofit.ApiClient;
import com.deals.dealapp.dialogs.LoadingDialogs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreFragment extends Fragment implements CategoryListAdapterr.ClickedItem{
    TextView searchtext;
    RecyclerView recyclerView;
    LoadingDialogs loadingDialogs;
    ArrayList<CategoryListModel> categoryListModelArrayList;
    CategoryListAdapterr categoryListAdapterr;

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
        initView(view);


        return view;
    }

    private void initView(View view) {
        loadingDialogs = new LoadingDialogs(getActivity());
        recyclerView = view.findViewById(R.id.recyclerView);
        searchtext = view.findViewById(R.id.searchtext);
        categoryListModelArrayList = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        categoryListAdapterr = new CategoryListAdapterr(this::ClickedUser);
       // GetCategoryList();
        GetCategoryList();
        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SearchActivity.class);
                startActivity(in);

            }
        });

    }


   /* @Override
    public void onCategoryListClickListener(int position) {

        //   categoryListModelArrayList.get(position).getId();
        //  categoryListModelArrayList.get(position).getCategoryname();
      *//*  Toast.makeText(getContext(), categoryListModelArrayList.get(position).getId() + "", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), categoryListModelArrayList.get(position).getCategoryname() + "", Toast.LENGTH_SHORT).show();
*//*

        SecondListStage secondListStage = new SecondListStage ();
        Bundle args = new Bundle();

        args.putString( "item",categoryListModelArrayList.get(position).getCategoryname());
        args.putString( "id",categoryListModelArrayList.get(position).getId());

        secondListStage.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.contentPanel, secondListStage).commit();


    }*/


    public void GetCategoryList() {
        loadingDialogs.startLoadingDialogs();

        Call<List<com.deals.dealapp.ModelResponse.CategoryListModel>> userlist = ApiClient.getUserService().getcategories();

        userlist.enqueue(new Callback<List<CategoryListModel>>() {
            @Override
            public void onResponse(Call<List<CategoryListModel>> call, Response<List<CategoryListModel>> response) {
                loadingDialogs.dismissDialog();
                if(response.isSuccessful()){
                    List<CategoryListModel> userResponses = response.body();
                    categoryListAdapterr.setData(userResponses);
                    recyclerView.setAdapter(categoryListAdapterr);


                }

            }

            @Override
            public void onFailure(Call<List<CategoryListModel>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
                loadingDialogs.dismissDialog();
            }
        });

    }


    @Override
    public void ClickedUser(com.deals.dealapp.ModelResponse.CategoryListModel userResponse) {

        SecondListStage secondListStage = new SecondListStage ();
        Bundle args = new Bundle();

        args.putString( "item",userResponse.getCategoryname());
        args.putInt( "id",userResponse.getId());
        //args.putString("data", String.valueOf(userResponse));

        secondListStage.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.contentPanel, secondListStage).commit();


    }
}