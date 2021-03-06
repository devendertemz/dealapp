package com.deals.dealapp.Fragments;

import android.app.Notification;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.Activity.Edit_profile;
import com.deals.dealapp.Activity.NotificationsActivity;
import com.deals.dealapp.Activity.ProdcutDeatails;
import com.deals.dealapp.Activity.SearchActivity;
import com.deals.dealapp.Activity.ThirdListStage;
import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.ModelResponse.Get_trending_categories_Response;
import com.deals.dealapp.ModelResponse.HomeSliderList;
import com.deals.dealapp.ModelResponse.HomepageSlider;
import com.deals.dealapp.ModelResponse.ProductDeatilsResponse;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.CategoryHomeAdapter;
import com.deals.dealapp.adapter.Get_trending_categories_Adapter;
import com.deals.dealapp.adapter.GridViewHomeCustomAdapter;
import com.deals.dealapp.adapter.HomeSliderAdapter;
import com.deals.dealapp.adapter.Jewallery_Adapterr;
import com.deals.dealapp.adapter.SlidingImageAdapter;
import com.deals.dealapp.adapter.UpgradeHome_Adapterr;
import com.deals.dealapp.databasee.Rtrofit.ApiClient;
import com.deals.dealapp.dialogs.LoadingDialogs;
import com.deals.dealapp.model.GridHomeModel;
import com.deals.dealapp.model.Item;
import com.deals.dealapp.model.JewalleryModel;
import com.deals.dealapp.model.JewalleryModelResponse;
import com.deals.dealapp.model.UpgradeHomeModel;
import com.deals.dealapp.util;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements CategoryHomeAdapter.ClickedItem {

    LinearLayout Search_layout;
    CardView searchbar;

    private RecyclerView recyclerView, jewellery_adapter_layout, UpgradehomeServicesList_RV;
    private ArrayList<HomepageSlider> arrayList;
    private ArrayList<Get_trending_categories_Response> get_trending_categories_responsesArray;
    Get_trending_categories_Response get_trending_categories_responsemodel;
    CircleImageView profile_image;
    ImageView notification;
    LoadingDialogs loadingDialogs;
    CategoryHomeAdapter categoryHomeAdapter;
    UpgradeHome_Adapterr upgradeHome_adapterr;

    Jewallery_Adapterr jewallery_adapterr;
    Get_trending_categories_Adapter get_trending_categories_adapter;
    List<JewalleryModel> jewalleryModelList;
    JewalleryModel jewalleryModel;


    private static ViewPager mpage;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int No_page = 0;
    RecyclerView topShoppingOffersGridView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    SlidingImageAdapter adapter;
    TextView heading, price;


    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initView(view);


        return view;

    }

    private void initView(View view) {
        // Search_layout = (LinearLayout) view.findViewById(R.id.search_layout);
        loadingDialogs = new LoadingDialogs(getActivity());

        util.blackiteamstatusbar(getActivity(), R.color.gray);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        jewellery_adapter_layout = (RecyclerView) view.findViewById(R.id.jewellery_adapter_layout);
        UpgradehomeServicesList_RV = (RecyclerView) view.findViewById(R.id.UpgradehomeServicesList);
        heading = view.findViewById(R.id.heading);
        price = view.findViewById(R.id.price);

        profile_image = view.findViewById(R.id.profile_image);
        notification = view.findViewById(R.id.notification);
        categoryHomeAdapter = new CategoryHomeAdapter(this::ClickedUser);
        upgradeHome_adapterr = new UpgradeHome_Adapterr(this::ClickedUser);
        jewallery_adapterr = new Jewallery_Adapterr(this::ClickedUser);
        get_trending_categories_adapter = new Get_trending_categories_Adapter(this::ClickedUser);
        topShoppingOffersGridView = view.findViewById(R.id.RV_upto_offecr);


        GridLayoutManager managertopShoppingOffersGridView = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        topShoppingOffersGridView.setLayoutManager(managertopShoppingOffersGridView);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        jewellery_adapter_layout.setLayoutManager(manager2);
        GridLayoutManager manager3 = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

        UpgradehomeServicesList_RV.setLayoutManager(manager3);
        searchbar = view.findViewById(R.id.searchbar);
        mpage = view.findViewById(R.id.imageSlider);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), SearchActivity.class);
                startActivity(in);


            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), NotificationsActivity.class);
                startActivity(in);

            }
        });
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Edit_profile.class);
                startActivity(in);

            }
        });
        // setRecyclerdata();
        GetCategoryList();
        setRecyclerdata();
        topShoppingOffersGridView();
        setjewellery_adapter_layout();
        setHomeupgradee_adapter_layout();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetCategoryList();
                setRecyclerdata();
                topShoppingOffersGridView();
                setjewellery_adapter_layout();
                setHomeupgradee_adapter_layout();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

      /*  Search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchFragment searchFragment = new SearchFragment();
                FragmentManager m = getFragmentManager();
                FragmentTransaction fragmentTransaction = m.beginTransaction();
                fragmentTransaction.replace(R.id.contentPanel, searchFragment);
                fragmentTransaction.commit();
            }
        });*/


    }

    private void ClickedUser(Get_trending_categories_Response get_trending_categories_response) {

    }

    private void ClickedUser(UpgradeHomeModel upgradeHomeModel) {
        Toast.makeText(getActivity(), upgradeHomeModel.getCategoryName() + "", Toast.LENGTH_SHORT).show();

    }

    private void ClickedUser(JewalleryModel jewalleryModel) {


    }

    public void setRecyclerdata() {

        arrayList = new ArrayList<>();

        Call<List<HomepageSlider>> userlist = ApiClient.getUserService().getHomepageslider();

        userlist.enqueue(new Callback<List<HomepageSlider>>() {
            @Override
            public void onResponse(Call<List<HomepageSlider>> call, Response<List<HomepageSlider>> response) {

                // Toast.makeText(getContext(), response.code()+"", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    List<HomepageSlider> userResponses = response.body();
                    for (int i = 0; i < userResponses.size(); i++) {
                        arrayList.add(userResponses.get(i));


                        // Toast.makeText(getContext(), userResponses.get(i).getHeading()+"", Toast.LENGTH_SHORT).show();


                       /* name.setText(  userResponses.get(i).getProductName());
                        finalPrice.setText("Rs."+userResponses.get(i).getActualAmount());
                        discountprice.setText("Rs."+userResponses.get(i).getPrice());
                        discount.setText(userResponses.get(i).getDiscount()+"% OFF");
                        product_desc.setText(HtmlCompat.fromHtml(userResponses.get(i).getProductDesc(), 0));

                        String url = "http://api.ourprive.com/" + userResponses.get(i).getImages();

                        Picasso.get().load(url).into(d_image);

                        Toast.makeText(ProdcutDeatails.this, userResponses.get(i).getCategoryname(), Toast.LENGTH_SHORT).show();
*/
                    }
                    if (getActivity() != null) {

                        adapter = new SlidingImageAdapter(arrayList, getContext());
                        mpage.setAdapter(adapter);
                        indicator.setViewPager(mpage);


                        final float density = getResources().getDisplayMetrics().density;
                        //Set circle indicator radius
                        indicator.setRadius(5 * density);
                        No_page = arrayList.size();
                        // Auto start of viewpager
                        final Handler handler = new Handler();
                        final Runnable Update = new Runnable() {
                            public void run() {
                                if (currentPage == No_page) {
                                    currentPage = 0;
                                }
                                mpage.setCurrentItem(currentPage++, true);
                            }
                        };
                        Timer swipeTimer = new Timer();
                        swipeTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(Update);
                            }
                        }, 3000, 3000);

                        // Pager listener over indicator
                        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                            @Override
                            public void onPageSelected(int position) {
                                currentPage = position;

                            }

                            @Override
                            public void onPageScrolled(int pos, float arg1, int arg2) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int pos) {

                            }
                        });
                        //   Toast.makeText(ProdcutDeatails.this, "right way", Toast.LENGTH_SHORT).show();


                  /*  secondcategory_adapterr.setData(userResponses);
                    recyclerView.setAdapter(secondcategory_adapterr);*/

                    }
                }
            }

            @Override
            public void onFailure(Call<List<HomepageSlider>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(getActivity(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });

        /*arrayList = new ArrayList<>();


        arrayList.add(new HomeSliderList("Groceries", "Up to 90% OFF", "    On Groceries Online", R.drawable.p2));
        arrayList.add(new HomeSliderList("Clothing", " Up to 80% OFF", "On Latest Fashion Clothing", R.drawable.p1));

        SlidingImageAdapter adapter = new SlidingImageAdapter(arrayList, getContext());
        mpage.setAdapter(adapter);
        indicator.setViewPager(mpage);
*/
    /*mpage.setAdapter(new HomeSliderAdapter(getContext(), arrayList));
    indicator.setViewPager(mpage);
*/


    }

    public void setjewellery_adapter_layout() {

        /// loadingDialogs.startLoadingDialogs();
/*
        Call<List<JewalleryModelResponse>> userlist = ApiClient.getUserService().getjewellery();

        userlist.enqueue(new Callback<List<JewalleryModelResponse>>() {

            @Override
            public void onResponse(Call<List<JewalleryModelResponse>> call, Response<List<JewalleryModelResponse>> response) {
          Toast.makeText(getActivity(), +response.code()+ "setjewellery_adapter_layout", Toast.LENGTH_SHORT).show();

                loadingDialogs.dismissDialog();

            }

            @Override
            public void onFailure(Call<List<JewalleryModelResponse>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
             Toast.makeText(getActivity(), t.getLocalizedMessage() + "onFailure", Toast.LENGTH_SHORT).show();
                loadingDialogs.dismissDialog();
            }
        });*/
        jewalleryModelList = new ArrayList<>();

        Call<ResponseBody> userlist = ApiClient.getUserService().getjewelleryy();
        userlist.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {

                    try {
                        s = response.body().string();
                       // Toast.makeText(getActivity(), s + "", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(s);
                        price.setText(jsonObject.getString("heading"));
                        heading.setText(jsonObject.getString("cate_desc"));
                        JSONArray jsonArray = jsonObject.getJSONArray("slider");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            jewalleryModel = new JewalleryModel(jsonObject1.getString("id"),
                                    jsonObject1.getString("category_name"),
                                    jsonObject1.getString("categorydesc"),
                                    jsonObject1.getString("image"),
                                    jsonObject1.getString("trending")
                            );
                            jewalleryModelList.add(jewalleryModel);

                        }
                        jewallery_adapterr.setData(jewalleryModelList);
                        jewellery_adapter_layout.setAdapter(jewallery_adapterr);

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }


                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorRes);
                        String err_msg = jsonObject.getString("error");
                        int status = jsonObject.getInt("status");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage() + "onFailure", Toast.LENGTH_SHORT).show();
                Log.e("failure", t.getLocalizedMessage());

            }
        });


    }


    public void setHomeupgradee_adapter_layout() {

        Call<List<UpgradeHomeModel>> userlist = ApiClient.getUserService().UpgradeHome();

        userlist.enqueue(new Callback<List<UpgradeHomeModel>>() {
            @Override
            public void onResponse(Call<List<UpgradeHomeModel>> call, Response<List<UpgradeHomeModel>> response) {
                if (response.isSuccessful()) {
                    List<UpgradeHomeModel> userResponses = response.body();
                    upgradeHome_adapterr.setData(userResponses);
                    UpgradehomeServicesList_RV.setAdapter(upgradeHome_adapterr);


                }

            }

            @Override
            public void onFailure(Call<List<UpgradeHomeModel>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(getActivity(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void topShoppingOffersGridView() {


       /* gridHomeModelArrayList = new ArrayList<>();
        gridHomeModelArrayList.add(new GridHomeModel("Eyewear", R.drawable.p3));
        gridHomeModelArrayList.add(new GridHomeModel("Back bags", R.drawable.p4));
        gridHomeModelArrayList.add(new GridHomeModel("Watches", R.drawable.p5));
        gridHomeModelArrayList.add(new GridHomeModel("Jewellery", R.drawable.p6));
        gridHomeModelArrayList.add(new GridHomeModel("Jewellery", R.drawable.p6));
        gridHomeModelArrayList.add(new GridHomeModel("Jewellery", R.drawable.p6));
        gridHomeModelArrayList.add(new GridHomeModel("Jewellery", R.drawable.p6));
        gridHomeModelArrayList.add(new GridHomeModel("Jewellery", R.drawable.p6));

        GridViewHomeCustomAdapter adapter = new GridViewHomeCustomAdapter(getContext(), gridHomeModelArrayList);
        topShoppingOffersGridView.setAdapter(adapter);
        topShoppingOffersGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), gridHomeModelArrayList.get(position).getName() + "" + position, Toast.LENGTH_SHORT).show();


                Intent in = new Intent(getContext(), ProdcutDeatails.class);
                in.putExtra("P_Name", gridHomeModelArrayList.get(position).getName());
                in.putExtra("P_Finalprice", "5675");
                in.putExtra("P_OfferPrice", "23");
                in.putExtra("P_Taxdeatils", " Price inclusive of all taxes");

                startActivity(in);

            }
        });*/

        get_trending_categories_responsesArray = new ArrayList<>();
        Call<List<Get_trending_categories_Response>> userlist = ApiClient.getUserService().Get_trending_categories();


        userlist.enqueue(new Callback<List<Get_trending_categories_Response>>() {
            @Override
            public void onResponse(Call<List<Get_trending_categories_Response>> call, Response<List<Get_trending_categories_Response>> response) {
                //Toast.makeText(getActivity(), +response.code()+ "", Toast.LENGTH_SHORT).show();

                loadingDialogs.dismissDialog();
                if (response.isSuccessful()) {
                    get_trending_categories_responsemodel = new Get_trending_categories_Response();
                    List<Get_trending_categories_Response> userResponses = response.body();
                    // Toast.makeText(getActivity(), userResponses.toString()+"", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < userResponses.size(); i++) {
                        get_trending_categories_responsemodel.setImage(userResponses.get(i).getImage());

                        get_trending_categories_responsemodel.setImage(userResponses.get(i).getCategoryName());
                        get_trending_categories_responsesArray.add(get_trending_categories_responsemodel);


                    }


                    get_trending_categories_adapter.setData(userResponses);
                    topShoppingOffersGridView.setAdapter(get_trending_categories_adapter);


                }

            }

            @Override
            public void onFailure(Call<List<Get_trending_categories_Response>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(getActivity(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                loadingDialogs.dismissDialog();
            }
        });



    /*mpage.setAdapter(new HomeSliderAdapter(getContext(), arrayList));
    indicator.setViewPager(mpage);
*/

    }

    public void GetCategoryList() {
        loadingDialogs.startLoadingDialogs();

        Call<List<CategoryListModel>> userlist = ApiClient.getUserService().getcategories();

        userlist.enqueue(new Callback<List<CategoryListModel>>() {
            @Override
            public void onResponse(Call<List<CategoryListModel>> call, Response<List<CategoryListModel>> response) {
                loadingDialogs.dismissDialog();
                if (response.isSuccessful()) {
                    List<CategoryListModel> userResponses = response.body();
                    categoryHomeAdapter.setData(userResponses);
                    recyclerView.setAdapter(categoryHomeAdapter);


                }

            }

            @Override
            public void onFailure(Call<List<CategoryListModel>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(getActivity(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                loadingDialogs.dismissDialog();
            }
        });

    }

    @Override
    public void ClickedUser(CategoryListModel userResponse) {

        SecondListStage secondListStage = new SecondListStage();
        Bundle args = new Bundle();

        args.putString("item", userResponse.getCategoryname());
        args.putInt("id", userResponse.getId());
        //args.putString("data", String.valueOf(userResponse));

        secondListStage.setArguments(args);
        getFragmentManager().beginTransaction().add(R.id.contentPanel, secondListStage).commit();

    }


}