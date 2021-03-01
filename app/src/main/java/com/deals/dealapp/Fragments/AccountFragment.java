package com.deals.dealapp.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.Activity.Edit_profile;
import com.deals.dealapp.Activity.Help_Center_Activity;
import com.deals.dealapp.Activity.LoginAcitivity;
import com.deals.dealapp.Activity.NotificationsActivity;
import com.deals.dealapp.Activity.subscriptionActivity;
import com.deals.dealapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

public class AccountFragment extends Fragment {

    LinearLayout ll_Orders, ll_Notifications, ll_profile, ll_help_center, ll_Subscription,buttonLogin;
    GoogleSignInClient mGoogleSignInClient;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initview(view);



        // getActivity().getSupportActionBar().setTitle();


   /*  setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" "); */
        return view;
    }

    private void initview(View view) {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        ll_Orders = view.findViewById(R.id.ll_Orders);
        ll_Notifications = view.findViewById(R.id.ll_Notifications);
        ll_profile = view.findViewById(R.id.ll_profile);
        ll_help_center = view.findViewById(R.id.ll_help_center);
        ll_Subscription = view.findViewById(R.id.Subscription);
        buttonLogin = view.findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogBox();
            }
        });



        ll_Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Order", Toast.LENGTH_SHORT).show();
            }
        });
        ll_Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), NotificationsActivity.class);
                startActivity(in);

            }
        });
        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in =new Intent(getContext(), Edit_profile.class);
                startActivity(in);
            }
        });
        ll_help_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getContext(), Help_Center_Activity.class);
                startActivity(in);            }
        });
        ll_Subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), subscriptionActivity.class);
                startActivity(in);            }
        });
    }

    private void signOutt() {


        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent intent = new Intent(getActivity(), LoginAcitivity.class);
                        startActivity(intent);
                    }
                });

    }

    public void AlertDialogBox() {

        //Logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        // set title
        alertDialogBuilder.setTitle("Dealapp");

        // set dialog message
        alertDialogBuilder.setIcon(R.mipmap.logo2);
        alertDialogBuilder
                .setMessage("Are you sure to Logout ? ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        signOutt();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}


