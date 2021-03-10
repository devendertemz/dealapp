package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.R;

public class ProdcutDeatails extends AppCompatActivity {

    TextView name,finalPrice,discount,taxdetails;
    ImageView heartblack,withoutblak,d_image;
    LinearLayout back,wishlist_ll;
    Button addtocart_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodcut_deatails);


        initView();

    }

    @SuppressLint("SetTextI18n")
    private void initView() {

        name=findViewById(R.id.d_Name);
        finalPrice=findViewById(R.id.d_price);
        discount=findViewById(R.id.d_Offer);
        taxdetails=findViewById(R.id.d_tax_details);
        d_image=findViewById(R.id.d_image);
        back=findViewById(R.id.back);
        withoutblak=findViewById(R.id.withoutblak);
        wishlist_ll=findViewById(R.id.wishlist_ll);
        heartblack=findViewById(R.id.heartblack);
        addtocart_btn=findViewById(R.id.addtocart);

        wishlist_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withoutblak.setVisibility(View.GONE);
                heartblack.setVisibility(View.VISIBLE);

            }
        });
        addtocart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(ProdcutDeatails.this, "Add cart", Toast.LENGTH_SHORT).show();



            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {

            if (getIntent()!=null)
            {
                name.setText(getIntent().getExtras().getString("P_Name"));
                finalPrice.setText("Rs. "+getIntent().getExtras().getString("P_Finalprice"));
                discount.setText(getIntent().getExtras().getString("P_OfferPrice")+"% off ");
                taxdetails.setText(getIntent().getExtras().getString("P_Taxdeatils"));

                // d_image.setImageBitmap(getIntent().getExtras().getString("P_Taxdeatils"));



            }

        }catch (Exception e)
        {

        }

    }


}