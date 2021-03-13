package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.ModelResponse.ProductDeatilsResponse;
import com.deals.dealapp.ModelResponse.Secondcategory_itemlist;
import com.deals.dealapp.R;
import com.deals.dealapp.databasee.Rtrofit.ApiClient;
import com.deals.dealapp.dialogs.LoadingDialogs;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdcutDeatails extends AppCompatActivity {

    TextView name, finalPrice, discount, taxdetails,discountprice,product_desc;
    ImageView heartblack, withoutblak, d_image;
    LinearLayout back, wishlist_ll;
    Button addtocart_btn;
    LoadingDialogs loadingDialogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodcut_deatails);


        initView();

    }

    @SuppressLint("SetTextI18n")
    private void initView() {


        loadingDialogs = new LoadingDialogs(this);
        try {

            if (getIntent()!=null)
            {

              int  id=getIntent().getExtras().getInt("id");
                GetCategoryList(id);
                /*
                finalPrice.setText("Rs. "+getIntent().getExtras().getString("P_Finalprice"));
                discount.setText(getIntent().getExtras().getString("P_OfferPrice")+"% off ");
                taxdetails.setText(getIntent().getExtras().getString("P_Taxdeatils"));
*/
                // d_image.setImageBitmap(getIntent().getExtras().getString("P_Taxdeatils"));



            }

        }catch (Exception e)
        {

        }

        name = findViewById(R.id.d_Name);
        finalPrice = findViewById(R.id.d_price);
        discount = findViewById(R.id.d_Offer);
        taxdetails = findViewById(R.id.d_tax_details);
        discountprice= findViewById(R.id.discountprice);
        product_desc= findViewById(R.id.product_desc);
        d_image = findViewById(R.id.d_image);
        back = findViewById(R.id.back);
        withoutblak = findViewById(R.id.withoutblak);
        wishlist_ll = findViewById(R.id.wishlist_ll);
        heartblack = findViewById(R.id.heartblack);
        addtocart_btn = findViewById(R.id.addtocart);

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


    }

    public void GetCategoryList(int id) {
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();

        loadingDialogs.startLoadingDialogs();

        Call<List<ProductDeatilsResponse>> userlist = ApiClient.getUserService().getProductDetails(String.valueOf(id));

        userlist.enqueue(new Callback<List<ProductDeatilsResponse>>() {
            @Override
            public void onResponse(Call<List<ProductDeatilsResponse>> call, Response<List<ProductDeatilsResponse>> response) {
                loadingDialogs.dismissDialog();
                Toast.makeText(ProdcutDeatails.this, response.code()+"", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    List<ProductDeatilsResponse> userResponses = response.body();
                    for (int i=0;i<userResponses.size();i++)
                    {
                        name.setText(  userResponses.get(i).getProductName());
                        finalPrice.setText("Rs."+userResponses.get(i).getActualAmount());
                        discountprice.setText("Rs."+userResponses.get(i).getPrice());
                        discount.setText(userResponses.get(i).getDiscount()+"% OFF");
                        product_desc.setText(HtmlCompat.fromHtml(userResponses.get(i).getProductDesc(), 0));

                        String url = "http://api.ourprive.com/" + userResponses.get(i).getImages();

                        Picasso.get().load(url).into(d_image);

                        Toast.makeText(ProdcutDeatails.this, userResponses.get(i).getCategoryname(), Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(ProdcutDeatails.this, "right way", Toast.LENGTH_SHORT).show();


                  /*  secondcategory_adapterr.setData(userResponses);
                    recyclerView.setAdapter(secondcategory_adapterr);*/

                }

            }

            @Override
            public void onFailure(Call<List<ProductDeatilsResponse>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                loadingDialogs.dismissDialog();
            }
        });

    }
}