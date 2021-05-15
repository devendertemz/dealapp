package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.MainActivity;
import com.deals.dealapp.ModelResponse.AddtoCart_Resp;
import com.deals.dealapp.ModelResponse.AddtoWishlist_Resp;
import com.deals.dealapp.ModelResponse.Get_trending_categories_Response;
import com.deals.dealapp.ModelResponse.ProductDeatilsResponse;
import com.deals.dealapp.ModelResponse.Secondcategory_itemlist;
import com.deals.dealapp.R;
import com.deals.dealapp.databasee.Rtrofit.ApiClient;
import com.deals.dealapp.dialogs.LoadingDialogs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdcutDeatails extends AppCompatActivity {

    TextView name, finalPrice, discount, taxdetails, discountprice, product_desc;
    ImageView withoutblak, d_image;
    LinearLayout back, wishlist_ll;
    Button addtocart_btn;
    LoadingDialogs loadingDialogs;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodcut_deatails);


        initView();

    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        loadingDialogs = new LoadingDialogs(this);
        name = findViewById(R.id.d_Name);
        finalPrice = findViewById(R.id.d_price);
        discount = findViewById(R.id.d_Offer);
        taxdetails = findViewById(R.id.d_tax_details);
        discountprice = findViewById(R.id.discountprice);
        product_desc = findViewById(R.id.product_desc);
        d_image = findViewById(R.id.d_image);
        back = findViewById(R.id.back);
        withoutblak = findViewById(R.id.withoutblak);
        wishlist_ll = findViewById(R.id.wishlist_ll);

        addtocart_btn = findViewById(R.id.addtocart);
        String user_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        try {

            if (getIntent() != null) {
                Bundle bundle = getIntent().getExtras();


                id = bundle.getString("id");

                name.setText(bundle.getString("product_name"));
                finalPrice.setText("Rs." + bundle.getString("actual_amount"));
                discountprice.setText("Rs." + bundle.getString("price"));
                discount.setText(bundle.getString("discount") + "% OFF");
                product_desc.setText(HtmlCompat.fromHtml(bundle.getString("product_desc"), 0));

                String url = "http://api.ourprive.com/" + bundle.getString("images");

                Picasso.get().load(url).into(d_image);


            }

        } catch (Exception e) {

        }

        wishlist_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddTOWishlist(user_id, "1", id);

            }
        });
        addtocart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddTOCart(user_id, "1", id);


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    private void AddTOCart(String user_id, String quantity, String product_id) {
/*

        Toast.makeText(this, user_id + "", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, product_id + "", Toast.LENGTH_SHORT).show();
*/


        Call<AddtoCart_Resp> userlist = ApiClient.getUserService().add_to_cart(product_id, quantity, user_id);
        userlist.enqueue(new Callback<AddtoCart_Resp>() {
            @Override
            public void onResponse(Call<AddtoCart_Resp> call, Response<AddtoCart_Resp> response) {
               // Toast.makeText(ProdcutDeatails.this, response.code() + "", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {
                        Toast.makeText(ProdcutDeatails.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProdcutDeatails.this, MainActivity.class));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddtoCart_Resp> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });
    /*

        userlist.enqueue(new Callback<List<AddtoCart_Resp>>() {
            @Override
            public void onResponse(Call<List<AddtoCart_Resp>> call, Response<List<AddtoCart_Resp>> response) {
                Toast.makeText(ProdcutDeatails.this, response.code()+"", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), +response.code()+ "", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<AddtoCart_Resp>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

*/
    }
    private void AddTOWishlist(String user_id, String quantity, String product_id) {
/*

        Toast.makeText(this, user_id + "", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, product_id + "", Toast.LENGTH_SHORT).show();
*/


        Call<AddtoWishlist_Resp> userlist = ApiClient.getUserService().AddtoWishlist_Resp(product_id, user_id);
        userlist.enqueue(new Callback<AddtoWishlist_Resp>() {
            @Override
            public void onResponse(Call<AddtoWishlist_Resp> call, Response<AddtoWishlist_Resp> response) {
                // Toast.makeText(ProdcutDeatails.this, response.code() + "", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {
                        withoutblak.setImageResource(R.drawable.heartbgblack);

                        Toast.makeText(ProdcutDeatails.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(ProdcutDeatails.this, MainActivity.class));
                        wishlist_ll.setEnabled(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddtoWishlist_Resp> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });
    /*

        userlist.enqueue(new Callback<List<AddtoCart_Resp>>() {
            @Override
            public void onResponse(Call<List<AddtoCart_Resp>> call, Response<List<AddtoCart_Resp>> response) {
                Toast.makeText(ProdcutDeatails.this, response.code()+"", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), +response.code()+ "", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<AddtoCart_Resp>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

*/
    }


}