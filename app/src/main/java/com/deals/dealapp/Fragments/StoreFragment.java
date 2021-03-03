package com.deals.dealapp.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.Activity.Edit_profile;
import com.deals.dealapp.Activity.LoginAcitivity;
import com.deals.dealapp.Activity.SearchActivity;
import com.deals.dealapp.Interface.CategoryListClickListner;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.CategoryListAdapter;
import com.deals.dealapp.adapter.Secondcategory_Adapter;
import com.deals.dealapp.databasee.RetrofitClient;
import com.deals.dealapp.dialogs.LoadingDialogs;
import com.deals.dealapp.model.CategoryListModel;
import com.irozon.sneaker.Sneaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreFragment extends Fragment implements CategoryListClickListner {
    TextView searchtext;
    RecyclerView recyclerView;
    LoadingDialogs loadingDialogs;
    ArrayList<CategoryListModel> categoryListModelArrayList;
    CategoryListAdapter categoryListAdapter;

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

        GetCategoryList();
        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SearchActivity.class);
                startActivity(in);

            }
        });

    }


    public void GetCategoryList() {

        loadingDialogs.startLoadingDialogs();


        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi().getcategories();
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                loadingDialogs.dismissDialog();


                //  Toast.makeText(getActivity(), response.code() + "", Toast.LENGTH_SHORT).show();

                if (response.code() == 200) {
                    try {

                        JSONArray jsonArray = new JSONArray(response.body().string());
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            CategoryListModel categoryListModel = new CategoryListModel();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            categoryListModel.setId(jsonObject.getString("id"));
                            categoryListModel.setCategoryname(jsonObject.getString("categoryname"));
                            categoryListModel.setImage(getString(R.string.imageurl) + jsonObject.getString("image"));
                            categoryListModel.setCategorydesc(jsonObject.getString("categorydesc"));
                            categoryListModelArrayList.add(categoryListModel);


                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }

                    categoryListAdapter = new CategoryListAdapter(categoryListModelArrayList, getContext(), StoreFragment.this::onCategoryListClickListener);
                    recyclerView.setAdapter(categoryListAdapter);
                    GridLayoutManager manager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(manager);

                   /*
                    categoryListAdapter = new CategoryListAdapter(categoryListModelArrayList,getContext(), StoreFragment.this::onCategoryListClickListener);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(categoryListAdapter);*/

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loadingDialogs.dismissDialog();

                Toast.makeText(getActivity(), call.toString() + "", Toast.LENGTH_SHORT).show();


            }

        });

    }


    @Override
    public void onCategoryListClickListener(int position) {

        //   categoryListModelArrayList.get(position).getId();
        //  categoryListModelArrayList.get(position).getCategoryname();
      /*  Toast.makeText(getContext(), categoryListModelArrayList.get(position).getId() + "", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), categoryListModelArrayList.get(position).getCategoryname() + "", Toast.LENGTH_SHORT).show();
*/

        SecondListStage secondListStage = new SecondListStage ();
        Bundle args = new Bundle();

        args.putString( "item",categoryListModelArrayList.get(position).getCategoryname());
        args.putString( "id",categoryListModelArrayList.get(position).getId());

        secondListStage.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.contentPanel, secondListStage).commit();


    }
}