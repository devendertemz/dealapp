package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.deals.dealapp.R;
import com.deals.dealapp.dialogs.ImageFilePath;
import com.deals.dealapp.dialogs.LoadingDialogs;
import com.deals.dealapp.dialogs.SelectPhotoDialog;
import com.irozon.sneaker.Sneaker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Edit_profile extends AppCompatActivity {

    EditText ed_name, ed_email, ed_mobile, ed_password;
    Button update_btn;
    String personNamee = "", personEmaill = "", mobile, password, confirm_password;

    LinearLayout changepassword;

    private static final int PICK_IMAGE = 100;


    Uri imageUri;
    FrameLayout Opengallery;
    ImageView iv_profile_photo;


    private ImageView ivProfilePhoto, backpress;

    LoadingDialogs loadingDialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initview();

    }

    private void initview() {
        loadingDialogs = new LoadingDialogs(this);
        ivProfilePhoto = (ImageView) findViewById(R.id.iv_profile_photo);
        backpress = (ImageView) findViewById(R.id.backpress);
        ed_name = findViewById(R.id.name);
        ed_email = findViewById(R.id.email);
        ed_mobile = findViewById(R.id.mobile);
        ed_password = findViewById(R.id.password);
       update_btn = findViewById(R.id.update);
        changepassword=findViewById(R.id.changepassword);
        Opengallery=findViewById(R.id.Opengallery);
        iv_profile_photo=findViewById(R.id.iv_profile_photo);
        Opengallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chengepassword=new Intent(Edit_profile.this,ChangePasswordActivity.class);
                startActivity(chengepassword);

            }
        });
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        try {

            if (getIntent() != null) {
                personNamee = getIntent().getExtras().getString("personName");
                personEmaill = getIntent().getExtras().getString("personEmail");


                ed_name.setText(personNamee);
                ed_email.setText(personEmaill);


            }

        } catch (Exception e) {

        }
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_validation();
            }
        });


    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            iv_profile_photo.setImageURI(imageUri);
        }
    }

    public void number_validation() {

        loadingDialogs.startLoadingDialogs();
        personNamee = ed_name.getText().toString().trim();
        personEmaill = ed_email.getText().toString().trim();
        mobile = ed_mobile.getText().toString().trim();
        password = ed_password.getText().toString().trim();
        if (personNamee.isEmpty()) {
            loadingDialogs.dismissDialog();


            Sneaker.with(Edit_profile.this)
                    .setTitle("Enter Full Name")
                    .setMessage("")
                    .sneakError();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(personEmaill).matches()) {
            loadingDialogs.dismissDialog();


            Sneaker.with(Edit_profile.this)
                    .setTitle("Enter Email")
                    .setMessage("")
                    .sneakError();
        } else if (mobile.isEmpty() || mobile.length() <10) {
            loadingDialogs.dismissDialog();


            Sneaker.with(Edit_profile.this)
                    .setTitle("Enter Mobile Number")
                    .setMessage("")
                    .sneakError();
        } else if (password.isEmpty()) {
            loadingDialogs.dismissDialog();


            Sneaker.with(Edit_profile.this)
                    .setTitle("Enter Password")
                    .setMessage("")
                    .sneakError();
        } else if (!password.equals(confirm_password)) {
            loadingDialogs.dismissDialog();

            Sneaker.with(Edit_profile.this)
                    .setTitle("Enter Confirm Password")
                    .setMessage("")
                    .sneakError();
        } else {

/*
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi().register(personNamee, personEmaill, mobile, password, "remember_token");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String s = null;
                    loadingDialogs.dismissDialog();
                    Toast.makeText(Edit_profile.this, response.code() + "", Toast.LENGTH_SHORT).show();

                    if (response.code() == 200) {

                        Toast.makeText(Edit_profile.this, "Registration Done Successfully", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(Edit_profile.this, LoginAcitivity.class);
                        startActivity(in);


                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    loadingDialogs.dismissDialog();


                }
            });*/

        }


    }
}