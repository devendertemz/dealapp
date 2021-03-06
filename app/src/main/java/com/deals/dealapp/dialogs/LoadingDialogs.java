package com.deals.dealapp.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.deals.dealapp.R;

public class LoadingDialogs {

   private Activity activity;
    private AlertDialog dialog;

    public LoadingDialogs
            (Activity activity) {
        this.activity = activity;
    }

    public void startLoadingDialogs()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.customdialog,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }



    public  void dismissDialog()
    {
        dialog.dismiss();
    }
}
