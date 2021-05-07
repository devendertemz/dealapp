package com.deals.dealapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.Activity.ProdcutDeatails;
import com.deals.dealapp.Activity.SearchActivity;

import com.deals.dealapp.Activity.ThirdListStage;
import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.ModelResponse.Secondcategory_itemlist;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.CategoryListAdapterr;
import com.deals.dealapp.adapter.Secondcategory_Adapter;
import com.deals.dealapp.adapter.Secondcategory_Adapterr;
import com.deals.dealapp.databasee.Rtrofit.ApiClient;
import com.deals.dealapp.databasee.Rtrofit.UserService;
import com.deals.dealapp.dialogs.LoadingDialogs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//implements Secondcategory_Adapter.ItemListener


public class SecondListStage extends Fragment  implements Secondcategory_Adapterr.ClickedItem{
    TextView categorgyname;
    private RecyclerView recyclerView;
    TextView searchtext;
    LoadingDialogs loadingDialogs;
    int id;
    Secondcategory_Adapterr secondcategory_adapterr;
    SwipeRefreshLayout mSwipeRefreshLayout;

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
        loadingDialogs = new LoadingDialogs(getActivity());

        searchtext=view.findViewById(R.id.searchtext);
        categorgyname=view.findViewById(R.id.categorgyname);
        recyclerView=view.findViewById(R.id.recycler_secondcategory);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        secondcategory_adapterr = new Secondcategory_Adapterr(this::ClickedUser);

        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), SearchActivity.class);
                startActivity(in);
            }
        });



        try {

            Bundle mBundle = new Bundle();
            mBundle = getArguments();

            String value = mBundle.getString("item");
             id = mBundle.getInt("id");




            categorgyname.setText(value);
            GetSubCategoryList();
        } catch (Exception e) {


        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetSubCategoryList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }/*

    public void setApparelRecyclerdata()
    {


        arrayList = new ArrayList<>();
        arrayList.add(new Secondcategory_itemlist("MAX","#E0E0E0",   R.drawable.groceries));
        arrayList.add(new Secondcategory_itemlist("H&M",  "#E0E0E0",R.drawable.logo2));
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


    }*/

    public void GetSubCategoryList() {
        loadingDialogs.startLoadingDialogs();

        Call<List<com.deals.dealapp.ModelResponse.Secondcategory_itemlist>> userlist = ApiClient.getUserService().getSubCategories(String.valueOf(id));

        userlist.enqueue(new Callback<List<Secondcategory_itemlist>>() {
            @Override
            public void onResponse(Call<List<Secondcategory_itemlist>> call, Response<List<Secondcategory_itemlist>> response) {
                loadingDialogs.dismissDialog();
                if(response.isSuccessful()){
                    List<Secondcategory_itemlist> userResponses = response.body();
                    secondcategory_adapterr.setData(userResponses);
                    recyclerView.setAdapter(secondcategory_adapterr);

                }

            }

            @Override
            public void onFailure(Call<List<Secondcategory_itemlist>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
                loadingDialogs.dismissDialog();
            }
        });



    }

    @Override
    public void ClickedUser(Secondcategory_itemlist userResponse) {

        Intent in=new Intent(getActivity(), ThirdListStage.class);
      /*  in.putExtra("P_Name",item.P_Name);
        in.putExtra("P_Finalprice",item.P_Finalprice);
        in.putExtra("P_OfferPrice",item.P_OfferPrice);
        in.putExtra("P_Taxdeatils",item.P_Taxdeatils);

        in.putExtra("P_Iamge",item.P_Iamge);*/
        in.putExtra("id",userResponse.getId());
        in.putExtra("item",userResponse.getSubcatename());
        startActivity(in);

//Toast.makeText(getContext(), userResponse.getId()+"", Toast.LENGTH_SHORT).show();


        //startActivity(new Intent(getContext(),ThirdListStage.class).putExtra("data", (Parcelable) userResponse));


    }


}