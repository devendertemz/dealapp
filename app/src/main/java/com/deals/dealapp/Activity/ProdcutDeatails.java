package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deals.dealapp.R;

public class ProdcutDeatails extends AppCompatActivity {

    TextView name,finalPrice,discount,taxdetails;
    ImageView d_image;
    LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodcut_deatails);
        name=findViewById(R.id.d_Name);
        finalPrice=findViewById(R.id.d_price);
        discount=findViewById(R.id.d_Offer);
        taxdetails=findViewById(R.id.d_tax_details);
        d_image=findViewById(R.id.d_image);
        back=findViewById(R.id.back);
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
                finalPrice.setText(getIntent().getExtras().getString("P_Finalprice"));
                discount.setText(getIntent().getExtras().getString("P_OfferPrice"));
                taxdetails.setText(getIntent().getExtras().getString("P_Taxdeatils"));

               // d_image.setImageBitmap(getIntent().getExtras().getString("P_Taxdeatils"));



            }

        }catch (Exception e)
        {

        }
    }


}