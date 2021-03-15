package com.deals.dealapp.Fragments;

import android.app.Notification;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
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
import android.widget.Toast;

import com.deals.dealapp.Activity.Edit_profile;
import com.deals.dealapp.Activity.NotificationsActivity;
import com.deals.dealapp.Activity.ProdcutDeatails;
import com.deals.dealapp.Activity.SearchActivity;
import com.deals.dealapp.Activity.ThirdListStage;
import com.deals.dealapp.ModelResponse.CategoryListModel;
import com.deals.dealapp.ModelResponse.HomeSliderList;
import com.deals.dealapp.R;
import com.deals.dealapp.adapter.CategoryHomeAdapter;
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
import com.deals.dealapp.model.UpgradeHomeModel;
import com.deals.dealapp.util;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements CategoryHomeAdapter.ClickedItem {

    LinearLayout Search_layout;
    CardView searchbar;

    private RecyclerView recyclerView, jewellery_adapter_layout, UpgradehomeServicesList_RV;
    private ArrayList<HomeSliderList> arrayList;
    private ArrayList<GridHomeModel> gridHomeModelArrayList;

    CircleImageView profile_image;
    ImageView notification;
    LoadingDialogs loadingDialogs;
    CategoryHomeAdapter categoryHomeAdapter;
    UpgradeHome_Adapterr upgradeHome_adapterr;

    Jewallery_Adapterr jewallery_adapterr;
    private static ViewPager mpage;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int No_page = 0;
    GridView topShoppingOffersGridView;
    SwipeRefreshLayout mSwipeRefreshLayout;
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


        profile_image = view.findViewById(R.id.profile_image);
        notification = view.findViewById(R.id.notification);
        categoryHomeAdapter = new CategoryHomeAdapter(this::ClickedUser);
        upgradeHome_adapterr = new UpgradeHome_Adapterr(this::ClickedUser);
        jewallery_adapterr = new Jewallery_Adapterr(this::ClickedUser);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        GridLayoutManager manager2 = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        jewellery_adapter_layout.setLayoutManager(manager2);
        GridLayoutManager manager3 = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

        UpgradehomeServicesList_RV.setLayoutManager(manager3);
        searchbar = view.findViewById(R.id.searchbar);
        mpage = view.findViewById(R.id.imageSlider);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        topShoppingOffersGridView = view.findViewById(R.id.topShoppingOffersGridView);


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

    private void ClickedUser(UpgradeHomeModel upgradeHomeModel) {
        Toast.makeText(getActivity(), upgradeHomeModel.getCategoryname()+"", Toast.LENGTH_SHORT).show();

    }

    private void ClickedUser(JewalleryModel jewalleryModel) {
        Toast.makeText(getActivity(), jewalleryModel.getCategoryname()+"", Toast.LENGTH_SHORT).show();



    }

    public void setRecyclerdata() {
        arrayList = new ArrayList<>();


        arrayList.add(new HomeSliderList("Groceries", "Up to 90% OFF", "    On Groceries Online", R.drawable.p2));
        arrayList.add(new HomeSliderList("Clothing", " Up to 80% OFF", "On Latest Fashion Clothing", R.drawable.p1));

        SlidingImageAdapter adapter = new SlidingImageAdapter(arrayList, getContext());
        mpage.setAdapter(adapter);
        indicator.setViewPager(mpage);

    /*mpage.setAdapter(new HomeSliderAdapter(getContext(), arrayList));
    indicator.setViewPager(mpage);
*/

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

    }

    public void setjewellery_adapter_layout() {
        /// loadingDialogs.startLoadingDialogs();

        Call<List<JewalleryModel>> userlist = ApiClient.getUserService().getjewellery();

        userlist.enqueue(new Callback<List<JewalleryModel>>() {
            @Override
            public void onResponse(Call<List<JewalleryModel>> call, Response<List<JewalleryModel>> response) {
                loadingDialogs.dismissDialog();
                if (response.isSuccessful()) {
                    List<JewalleryModel> userResponses = response.body();
                    jewallery_adapterr.setData(userResponses);
                    jewellery_adapter_layout.setAdapter(jewallery_adapterr);


                }

            }

            @Override
            public void onFailure(Call<List<JewalleryModel>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Toast.makeText(getActivity(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                loadingDialogs.dismissDialog();
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
                    UpgradehomeServicesList_RV .setAdapter(upgradeHome_adapterr);


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


        gridHomeModelArrayList = new ArrayList<>();
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