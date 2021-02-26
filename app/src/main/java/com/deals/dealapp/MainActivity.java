package com.deals.dealapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.deals.dealapp.Activity.LoginAcitivity;
import com.deals.dealapp.Fragments.AccountFragment;
import com.deals.dealapp.Fragments.BagFragment;
import com.deals.dealapp.Fragments.HomeFragment;
import com.deals.dealapp.Fragments.StoreFragment;
import com.deals.dealapp.Fragments.WishlistFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {
    public static BottomNavigationView navigation;

    LinearLayout My_Order, My_Reward, My_Walllet, My_Cart;
    private Menu nav_menu;

    LinearLayout viewpa;
    TextView mTitle, username;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        util.blackiteamstatusbar(this, R.color.light_background);
        initComponent();

        HomeFragment appNewsHome1Fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contentPanel, appNewsHome1Fragment);
        transaction.commit();


            //the method we have create in activity
//            applyFontToMenuItem(mi);
        }

       /* My_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "My_Order", Toast.LENGTH_SHORT).show();

               *//* if (sessionManagement.isLoggedIn()) {
                    Intent intent=new Intent(MainActivity.this,My_Order_activity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }*//*
            }
        });
        My_Reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "My_Reward", Toast.LENGTH_SHORT).show();


               *//* if (sessionManagement.isLoggedIn()) {

                    drawer.closeDrawer(GravityCompat.START);

                    Reward_fragment fm = new Reward_fragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.contentPanel, fm);
                    transaction.commit();

//                    android.app.FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
//                            .addToBackStack(null).commit();
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
*//*
            }
        });
        My_Walllet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  if (sessionManagement.isLoggedIn()) {

                    drawer.closeDrawer(GravityCompat.START);

                    Wallet_fragment fm = new Wallet_fragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.contentPanel, fm);
                    transaction.commit();


//                    Wallet_fragment fm = new Wallet_fragment();
//                    android.app.FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
//                            .addToBackStack(null).commit();
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }*//*
                Toast.makeText(MainActivity.this, "My_Walllet", Toast.LENGTH_SHORT).show();

            }
        });

        My_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
*//*
                if (dbcart.getCartCount() > 0) {
                    CartFragment favourite_fragment = new CartFragment();
                    FragmentManager manager1 = getSupportFragmentManager();
                    FragmentTransaction transaction1 = manager1.beginTransaction();
                    transaction1.replace(R.id.contentPanel, favourite_fragment);
                    transaction1.addToBackStack(null);
                    transaction1.commit();
                } else {
                    Toast.makeText(MainActivity.this, "No Item in Cart", Toast.LENGTH_SHORT).show();
                }*//*
                Toast.makeText(MainActivity.this, "My_Cart", Toast.LENGTH_SHORT).show();

            }
        });*/
       /* iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*if (sessionManagement.isLoggedIn()) {
                    Edit_profile_fragment fm = new Edit_profile_fragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
                            .addToBackStack(null).commit();
                } else {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }*//*
                Toast.makeText(MainActivity.this, "iv_profile", Toast.LENGTH_SHORT).show();

            }
        });
*/


        // checkConnection();

        // updateHeader();
//sideMenu();

    @Override
    public boolean onSupportNavigateUp() {
       /* NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();*/
        return false;
    }

    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentPanel, fragment)
                .commitAllowingStateLoss();
    }

    private void initComponent() {
        navigation = (BottomNavigationView) findViewById(R.id.nav_view12);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        loadFragment(new HomeFragment());

                        HomeFragment appNewsHome1Fragment = new HomeFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.contentPanel, appNewsHome1Fragment);
                        transaction.commit();
                        return true;
                    case R.id.navigation_store:

                         //   Toast.makeText(MainActivity.this, "navigation_store", Toast.LENGTH_SHORT).show();
                        StoreFragment storeFragment = new StoreFragment();
                        FragmentManager manager2 = getSupportFragmentManager();
                        FragmentTransaction transaction2 = manager2.beginTransaction();
                        transaction2.replace(R.id.contentPanel, storeFragment);
                        transaction2.commit();


/*
                        CategoryFragment category_fragment = new CategoryFragment();
                        FragmentManager manager2 = getSupportFragmentManager();
                        FragmentTransaction transaction2 = manager2.beginTransaction();
                        transaction2.replace(R.id.contentPanel, category_fragment);
                        transaction2.commit();*/
                        return true;

                    case R.id.navigation_account:
                        Toast.makeText(MainActivity.this, "navigation_account", Toast.LENGTH_SHORT).show();

                        AccountFragment accountFragment = new AccountFragment();
                        FragmentManager m = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = m.beginTransaction();
                        fragmentTransaction.replace(R.id.contentPanel, accountFragment);
                        fragmentTransaction.commit();
                        return true;


                    case R.id.navigation_wishlist:
                        Toast.makeText(MainActivity.this, "navigation_wishlist", Toast.LENGTH_SHORT).show();

                        WishlistFragment wishlistFragment = new WishlistFragment();
                        FragmentManager m4 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionnn = m4.beginTransaction();
                        fragmentTransactionnn.replace(R.id.contentPanel, wishlistFragment);
                        fragmentTransactionnn.commit();
/*
                        NotificationFragment nf = new NotificationFragment();
                        FragmentManager m4 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransactionnn = m4.beginTransaction();
                        fragmentTransactionnn.replace(R.id.contentPanel, nf);
                        fragmentTransactionnn.commit();*/
                        return true;
//                    case R.id.navigation_newsstand:
////                        mTextMessage.setText(item.getTitle());
//                        navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
//                        window.setStatusBarColor(getResources().getColor(R.color.teal_800));
//                        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.teal_800));
//
//                        EditorPicked_Fragment editorPicked_fragment = new EditorPicked_Fragment();
//                        FragmentManager manager3 = getSupportFragmentManager();
//                        FragmentTransaction transaction3 = manager3.beginTransaction();
//                        transaction3.replace(R.id.contentPanel, editorPicked_fragment);
//                        transaction3.commit();
//                        return true;
                    case R.id.navigation_bag:

                        Toast.makeText(MainActivity.this, "navigation_bag", Toast.LENGTH_SHORT).show();
                        BagFragment bagFragment = new BagFragment();
                        FragmentManager manager1 = getSupportFragmentManager();
                        FragmentTransaction transaction1 = manager1.beginTransaction();
                        transaction1.replace(R.id.contentPanel, bagFragment);
                        transaction1.commit();
                     /*   CartFragment favourite_fragment = new CartFragment();
                        FragmentManager manager1 = getSupportFragmentManager();
                        FragmentTransaction transaction1 = manager1.beginTransaction();
                        transaction1.replace(R.id.contentPanel, favourite_fragment);
                        transaction1.commit();*/
                        return true;
                }
                return false;
            }
        });
    }


    public void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi friends i am using ." + " http://play.google.com/store/apps/details?id=" + getPackageName() + " APP"); //getPackageName()
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void sideMenu() {

        /*if (sessionManagement.isLoggedIn()) {
            //  tv_number.setVisibility(View.VISIBLE);
            nav_menu.findItem(R.id.nav_logout).setVisible(true);
            nav_menu.findItem(R.id.nav_my_profile).setVisible(true);
            //   nav_menu.findItem(R.id.login).setVisible(true);
            nav_menu.findItem(R.id.sign).setVisible(false);
            nav_menu.findItem(R.id.nav_powerd).setVisible(true);

            username.setText("Welcome! " +
                    ""+sessionManagement.getUserDetails().get(BaseURL.KEY_NAME));

//            nav_menu.findItem(R.id.signup).setVisible(false);

//            nav_menu.findItem(R.id.nav_user).setVisible(true);
        } else {

            //tv_number.setVisibility(View.GONE);
//            tv_name.setText(getResources().getString(R.string.btn_login));
//            tv_name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(i);
//                }
//            });
            nav_menu.findItem(R.id.login).setVisible(false);
            nav_menu.findItem(R.id.nav_my_profile).setVisible(false);
            nav_menu.findItem(R.id.nav_logout).setVisible(false);
            nav_menu.findItem(R.id.sign).setVisible(true);


            //            nav_menu.findItem(R.id.nav_user).setVisible(false);
        }
    }*/


//    public void updateHeader() {
//        if (sessionManagement.isLoggedIn()) {
//            String getname = sessionManagement.getUserDetails().get(BaseURL.KEY_NAME);
//            String getimage = sessionManagement.getUserDetails().get(BaseURL.KEY_IMAGE);
//            String getemail = sessionManagement.getUserDetails().get(BaseURL.KEY_EMAIL);
//            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
//            String previouslyEncodedImage = shre.getString("image_data", "");
//            if (!previouslyEncodedImage.equalsIgnoreCase("")) {
//                byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
//                iv_profile.setImageBitmap(bitmap);
//            }
//            Glide.with(this)
//                    .load(BaseURL.IMG_PROFILE_URL + getimage)
//                    .placeholder(R.drawable.icon)
//                    .crossFade()
//                    .into(iv_profile);
        //  tv_name.setText(getname);

//        }
//    }


   /* public void setCartCounter(String totalitem) {
        try {
            totalBudgetCount.setText(totalitem);
        }catch (Exception e){

        }
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem item = menu.findItem(R.id.action_cart);
        item.setVisible(true);

       View count = item.getActionView();
        totalBudgetCount = (TextView) count.findViewById(R.id.actionbar_notifcation_textview);
        count.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                menu.performIdentifierAction(item.getItemId(), 0);
            }
        });


        totalBudgetCount.setText("" + dbcart.getCartCount());
        return true;
    }*/
    }

    @Override
    public void onBackPressed() {

      /*
*/


        Fragment fr=getSupportFragmentManager().findFragmentById(R.id.contentPanel);
        String fragmentName = fr.getClass().getSimpleName();
        //Log.d("fragment name", "aa"+fragmentName);
       if (fragmentName.equals("HomeFragment")){

           Intent a = new Intent(Intent.ACTION_MAIN);
           a.addCategory(Intent.CATEGORY_HOME);
           a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(a);
       }
       else {
           HomeFragment appNewsHome1Fragment = new HomeFragment();
           FragmentManager manager = getSupportFragmentManager();
           FragmentTransaction transaction = manager.beginTransaction();
           transaction.replace(R.id.contentPanel, appNewsHome1Fragment);
           transaction.commit();
       }
        //super.onBackPressed();

    }

}
