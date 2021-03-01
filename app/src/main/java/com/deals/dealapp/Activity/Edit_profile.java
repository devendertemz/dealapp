package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.deals.dealapp.MainActivity;
import com.deals.dealapp.R;
import com.deals.dealapp.databasee.RetrofitClient;
import com.deals.dealapp.dialogs.ImageFilePath;
import com.deals.dealapp.dialogs.LoadingDialogs;
import com.deals.dealapp.dialogs.SelectPhotoDialog;
import com.irozon.sneaker.Sneaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_profile extends AppCompatActivity {

    EditText ed_name, ed_email, ed_mobile, ed_password;
    Button update_btn;
    String personNamee = "", personEmaill = "", mobile, password, confirm_password;
    ImageView iv_profile;
    ImageView changeImage;
    LinearLayout changepassword;


    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    protected boolean hasReadWritePermissions;
    protected static final int REQUEST_PERMISSIONS_READ_WRITE = 4;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private static final int REQUEST_IMAGE_CAMERA = 1;
    private String imagePath = "";
    private String displayPicImage = "";
    private ImageView ivProfilePhoto, backpress;

    protected boolean hasReadContactsPermissions;
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
        iv_profile = findViewById(R.id.iv_profile_photo);
        changeImage = findViewById(R.id.changeImage);
        changepassword=findViewById(R.id.changepassword);
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

    /*    changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);*//*

                SelectPhotoDialog selectPhotoDialog = new SelectPhotoDialog(get);
                selectPhotoDialog.setSelectPhotoDialogActionListener(new SelectPhotoDialog.SelectPhotoDialogActionListener() {
                    @Override
                    public void onSelectGalleryClick() {
                        onAddProfilePhotoFromGallery();

                    }

                    @Override
                    public void onSelectCameraClick() {
                        onAddProfilePhotoFromCamera();
                    }
                });
                selectPhotoDialog.show();
            }
        });*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAMERA && resultCode == RESULT_OK) {
            displayPicImage = imagePath;
            //    setBannerPic(tempImagePath);
            setDisplayPic(imagePath);
        }

        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {

            String imageFilePath = ImageFilePath.getPath(getApplicationContext(), data.getData());
            System.out.println(imageFilePath);

            displayPicImage = imageFilePath;
            setDisplayPic(imageFilePath);

        }

    }

    private void setDisplayPic(String tempImagePath) {

        Glide.with(getApplicationContext())
                .load(tempImagePath)
                .apply(new RequestOptions()
                        .error(R.drawable.ic_dummy_photo)
                        .fallback(R.drawable.ic_dummy_photo)
                        .centerCrop()
                        .circleCrop())
                .into(ivProfilePhoto);


    }

    public void onPhotoEditClick(final View view) {

        SelectPhotoDialog selectPhotoDialog = new SelectPhotoDialog(this);
        selectPhotoDialog.setSelectPhotoDialogActionListener(new SelectPhotoDialog.SelectPhotoDialogActionListener() {
            @Override
            public void onSelectGalleryClick() {
                onAddProfilePhotoFromGallery();

            }

            @Override
            public void onSelectCameraClick() {
                onAddProfilePhotoFromCamera();
            }
        });
        selectPhotoDialog.show();
    }


    public void onAddProfilePhotoFromCamera() {

        if (!checkForReadWritePermissions()) {
            getReadWritePermissions();
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile(0);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                if (photoFile != null) {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                    } else {
                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                                getApplicationContext().getPackageName() + ".provider", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    }
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAMERA);

                }
            }
        }
    }

    private File createImageFile(int op) throws IOException {
        File image = null;

        if (op == 0) {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());
            String imageFileName = "LaTaxi" + timeStamp + "_";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File storageDir = new File(
                        Environment.getExternalStorageDirectory() + "/LaTaxi/Photo/");
                if (!storageDir.exists()) {
                    storageDir.mkdirs();
                }
                image = new File(storageDir + imageFileName + ".jpg");
            } else {
                image = new File(getFilesDir() + "/" + imageFileName + ".jpg");
            }

            image.createNewFile();
            // Save a file: path for use with ACTION_VIEW intents
            imagePath = image.getAbsolutePath();
        }
        return image;
    }

    public void onAddProfilePhotoFromGallery() {

        if (!checkForReadWritePermissions()) {
            getReadWritePermissions();

        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY);

        }
    }

    protected boolean checkForReadWritePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                /*String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS_READ_WRITE);*/
                return hasReadWritePermissions = false;
            } else {
                return hasReadWritePermissions = true;
            }
        } else {
            return hasReadWritePermissions = true;
        }
    }

    protected void getReadWritePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS_READ_WRITE);
            }
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