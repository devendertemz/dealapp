package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.Interface.ItemClickListener;
import com.deals.dealapp.ModelResponse.Get_trending_categories_Response;
import com.deals.dealapp.ModelResponse.Getproducts_bybrandResp;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.Get_trending_categories_Adapter;
import com.deals.dealapp.adapter.Getproducts_bybrand_Adapter;
import com.deals.dealapp.adapter.ProductListAdapter;
import com.deals.dealapp.adapter.ProductOptionListAdapter;
import com.deals.dealapp.databasee.Rtrofit.ApiClient;
import com.deals.dealapp.model.ProductList;
import com.deals.dealapp.model.ProductListOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class ThirdListStage extends AppCompatActivity implements ProductListAdapter.ItemListener, ProductOptionListAdapter.ItemListener {

    LinearLayout back;

    TextView categorgyname;
    RecyclerView productRv, optionFilter;
    private ArrayList<ProductList> arrayList;
    private ArrayList<ProductListOptions> aarylist_productListOptions;
    Getproducts_bybrandResp getproducts_bybrandResp;
    private ArrayList<Getproducts_bybrandResp> getproductsBybrandRespArrayList;
    String prodcttype;
    Getproducts_bybrand_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_list_stage);
        initview();


    }

    private void initview() {

        categorgyname = findViewById(R.id.categorgyname);
        productRv = findViewById(R.id.productRecyerlerView);
        optionFilter = findViewById(R.id.recycler_productfilter);
        back = findViewById(R.id.back);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        productRv.setLayoutManager(manager);
        //setprodcutlRecyclerdata();
        SetProductFilterOptionRecyclerdata();
        try {
            int id = getIntent().getExtras().getInt("id");

            String item = getIntent().getExtras().getString("item");

            ///Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
            getproducts_bybrand(String.valueOf(id));
            categorgyname.setText(item);

        } catch (Exception e) {


        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void SetProductFilterOptionRecyclerdata() {


        aarylist_productListOptions = new ArrayList<>();
        aarylist_productListOptions.add(new ProductListOptions("men"));

        aarylist_productListOptions.add(new ProductListOptions("women"));
        aarylist_productListOptions.add(new ProductListOptions("boys"));
        aarylist_productListOptions.add(new ProductListOptions("girls"));


        ProductOptionListAdapter adapter = new ProductOptionListAdapter(aarylist_productListOptions, this, this);
        //ProductListAdapter adapter = new ProductListAdapter(arrayList,this );
        optionFilter.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        optionFilter.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(ProductList item) {
       /* Toast.makeText(this, item.getP_Iamge() + "", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(ThirdListStage.this, ProdcutDeatails.class);
        in.putExtra("P_Name", item.P_Name);
        in.putExtra("P_Finalprice", item.P_Finalprice);
        in.putExtra("P_OfferPrice", item.P_OfferPrice);
        in.putExtra("P_Taxdeatils", item.P_Taxdeatils);

        in.putExtra("P_Iamge", item.P_Iamge);
        startActivity(in);
*/
    }

    @Override
    public void onItemClick(ProductListOptions item) {
        prodcttype = item.getFliteroption();
        adapter.getFilter().filter(item.getFliteroption());

        Toast.makeText(this, item.getFliteroption() + "", Toast.LENGTH_SHORT).show();


    }


    public void getproducts_bybrand(String id) {


        getproductsBybrandRespArrayList = new ArrayList<>();

        Call<ResponseBody> call = ApiClient.getUserService().getproducts_bybrand(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Toast.makeText(ThirdListStage.this,  response.code() , Toast.LENGTH_SHORT).show();
                String s = null;

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    // Toast.makeText(ThirdListStage.this, response.message()+"", Toast.LENGTH_SHORT).show();
                    try {
                        s = response.body().string();

                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            getproducts_bybrandResp = new Getproducts_bybrandResp();

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            getproducts_bybrandResp.setId(jsonObject1.getString("id"));
                            getproducts_bybrandResp.setCatId(jsonObject1.getString("cat_id"));
                            getproducts_bybrandResp.setSubcatId(jsonObject1.getString("subcat_id"));
                            getproducts_bybrandResp.setProductName(jsonObject1.getString("product_name"));
                            getproducts_bybrandResp.setProductType(jsonObject1.getString("product_type"));
                            getproducts_bybrandResp.setImages(jsonObject1.getString("images"));
                            getproducts_bybrandResp.setProductDesc(jsonObject1.getString("product_desc"));
                            getproducts_bybrandResp.setPrice(jsonObject1.getString("price"));
                            getproducts_bybrandResp.setDiscount(jsonObject1.getString("discount"));
                            getproducts_bybrandResp.setActualAmount(jsonObject1.getString("actual_amount"));
                            getproductsBybrandRespArrayList.add(getproducts_bybrandResp);
                            ItemClickListener itemClickListener = new ItemClickListener() {
                                @Override
                                public void onClick(View view, int position, boolean isLongClick) {
                                    Intent i = new Intent(ThirdListStage.this, ProdcutDeatails.class);


                                    Bundle bundle = new Bundle();
                                    //Add your data from getFactualResults method to bundle
                                    bundle.putString("id", getproductsBybrandRespArrayList.get(position).getId());
                                    bundle.putString("cat_id", getproductsBybrandRespArrayList.get(position).getCatId());

                                    bundle.putString("subcat_id", getproductsBybrandRespArrayList.get(position).getSubcatId());
                                    bundle.putString("product_name", getproductsBybrandRespArrayList.get(position).getProductName());
                                    bundle.putString("images", getproductsBybrandRespArrayList.get(position).getImages());
                                    bundle.putString("product_desc", getproductsBybrandRespArrayList.get(position).getProductDesc());
                                    bundle.putString("price", getproductsBybrandRespArrayList.get(position).getPrice());
                                    bundle.putString("discount", getproductsBybrandRespArrayList.get(position).getDiscount());
                                    bundle.putString("actual_amount", getproductsBybrandRespArrayList.get(position).getActualAmount());
                                    //Add the bundle to the intent
                                    i.putExtras(bundle);

                                    //Fire the second activity
                                    startActivity(i);
                                    // SharedPrefManager.getInstans(getActivity()).datakyc_id(customerList.get(position).getId());
                                    //Toast.makeText(getContext(), familylist.get(position).getId()+"", Toast.LENGTH_SHORT).show();

                                    //  deleteQuery(customerList.get(position).getLead_type());
                                    //   deleteAlert(customerList.get(position).getId());

                                   // Toast.makeText(ThirdListStage.this, getproductsBybrandRespArrayList.get(position).getProductName() + "", Toast.LENGTH_SHORT).show();


                                }
                            };


                             adapter = new Getproducts_bybrand_Adapter(getproductsBybrandRespArrayList, itemClickListener);

                            //ProductListAdapter adapter = new ProductListAdapter(arrayList,this );
                            productRv.setAdapter(adapter);
                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }


                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(ThirdListStage.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });



    /*mpage.setAdapter(new HomeSliderAdapter(getContext(), arrayList));
    indicator.setViewPager(mpage);
*/

    }
}