package com.deals.dealapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.MainActivity;
import com.deals.dealapp.R;

import java.util.ArrayList;
import java.util.List;

public class BagFragment extends Fragment {


    RecyclerView recyclerView;
    LinearLayout ll_Checkout;
 //   CartAdapter cartAdapter;
    RelativeLayout viewCart;
    TextView totalItems;
    public static   TextView tv_total ;


    TextView btn_Checkout;

    //private List<CartModel> cartList = new ArrayList<>();
   // private DatabaseHandler db;
    //private Session_management sessionManagement;

    public BagFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bag, container, false);
        //initview(view);
        return view;
    }

    private void initview(View view)
    {
        btn_Checkout=view.findViewById(R.id.btn_Checkout);
        recyclerView=view.findViewById(R.id.recyclerCart);
        viewCart=view.findViewById(R.id.viewCartItems);
        tv_total=view.findViewById(R.id.txt_totalamount);
        totalItems=view.findViewById(R.id.txt_totalQuan);
        ll_Checkout=view.findViewById(R.id.ll_Checkout);

       // ll_Checkout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        btn_Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });




    }



}