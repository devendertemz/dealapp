package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.deals.dealapp.R;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerSearch;
    EditText txtSearch;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerSearch=findViewById(R.id.recyclerSearch);
        txtSearch=findViewById(R.id.txtSearch);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        // progressDialog.show();


            txtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (s.toString().length() >= 2) {
                        // searchlist.clear();
                        //searchUrl(String.valueOf(s));
                    }
                    else if (s.toString().length() < 2) {
                     /*   searchlist.clear();
                        if (searchAdapter != null) {
                            searchAdapter.notifyDataSetChanged();
                        }*/
                    }
                }
            });
        }
}