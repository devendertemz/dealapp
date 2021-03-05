package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.R;
import com.deals.dealapp.adapter.ProductListAdapter;
import com.deals.dealapp.adapter.ProductOptionListAdapter;
import com.deals.dealapp.model.ProductList;
import com.deals.dealapp.model.ProductListOptions;

import java.util.ArrayList;

public class ThirdListStage extends AppCompatActivity  implements ProductListAdapter.ItemListener,ProductOptionListAdapter.ItemListener{

            LinearLayout back;

        TextView categorgyname;
        RecyclerView productRv,optionFilter;
    private ArrayList<ProductList> arrayList;
    private ArrayList<ProductListOptions> aarylist_productListOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_list_stage);
        initview();



    }

    private void initview() {

        categorgyname=findViewById(R.id.categorgyname);
        productRv=findViewById(R.id.productRecyerlerView);
        optionFilter=findViewById(R.id.recycler_productfilter);
        back=findViewById(R.id.back);
        setprodcutlRecyclerdata();
       SetProductFilterOptionRecyclerdata();
        try {


            String item = getIntent().getExtras().getString("item");
            categorgyname.setText(item+" FASHION");
        } catch (Exception e) {


        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setprodcutlRecyclerdata()
    {


        arrayList = new ArrayList<>();
        arrayList.add(new ProductList("Ombre-Dyed Top ","Rs. 9866", " Rs. 2996 (58% off)"  ," Price inclusive of all taxes",R.drawable.p1));
        arrayList.add(new ProductList("Ombre-Dyed Top with Typography","Rs. 96", " Rs. 299 (68% off)"  ," Price inclusive of all taxes",R.drawable.p2));
        arrayList.add(new ProductList("Ombre-Dyed Top ","Rs. 9866", " Rs. 2996 (58% off)"  ," Price inclusive of all taxes",R.drawable.p1));
        arrayList.add(new ProductList("Ombre-Dyed Top with Typography","Rs. 96", " Rs. 299 (68% off)"  ," Price inclusive of all taxes",R.drawable.p2));
        arrayList.add(new ProductList("Ombre-Dyed Top ","Rs. 9866", " Rs. 2996 (58% off)"  ," Price inclusive of all taxes",R.drawable.p1));

        //.add(new ProductList("Ombre-Dyed Top with Typography","Rs. 96", " Rs. 299 (68% off)"  ," Price inclusive of all taxes",R.drawable.logo2));


        ProductListAdapter adapter = new ProductListAdapter(arrayList,this,this );
        //ProductListAdapter adapter = new ProductListAdapter(arrayList,this );
        productRv.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        productRv.setLayoutManager(manager);

    }


    public void SetProductFilterOptionRecyclerdata()
    {


        aarylist_productListOptions = new ArrayList<>();
        aarylist_productListOptions.add(new ProductListOptions("MEN"));

        aarylist_productListOptions.add(new ProductListOptions("WOMEN"));
        aarylist_productListOptions.add(new ProductListOptions("BOYS"));
        aarylist_productListOptions.add(new ProductListOptions("Girls"));



        ProductOptionListAdapter adapter = new ProductOptionListAdapter(aarylist_productListOptions,this,this );
        //ProductListAdapter adapter = new ProductListAdapter(arrayList,this );
        optionFilter.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        optionFilter.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(ProductList item) {
        Toast.makeText(this, item.getP_Iamge()+"", Toast.LENGTH_SHORT).show();
        Intent in=new Intent(ThirdListStage.this,ProdcutDeatails.class);
        in.putExtra("P_Name",item.P_Name);
        in.putExtra("P_Finalprice",item.P_Finalprice);
        in.putExtra("P_OfferPrice",item.P_OfferPrice);
        in.putExtra("P_Taxdeatils",item.P_Taxdeatils);

        in.putExtra("P_Iamge",item.P_Iamge);
        startActivity(in);

    }

    @Override
    public void onItemClick(ProductListOptions item) {
        Toast.makeText(this, item.getFliteroption()+"", Toast.LENGTH_SHORT).show();
    }
}