package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deals.dealapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class subscriptionActivity extends AppCompatActivity {
    LinearLayout back;
    TextView ll_AllnewPlans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        ll_AllnewPlans=findViewById(R.id.allnewPlans);

        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ll_AllnewPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);




                BottomSheetDialog dialog = new BottomSheetDialog(subscriptionActivity.this);
                dialog.setContentView(view1);
                dialog.show();
            }
        });
    }
}