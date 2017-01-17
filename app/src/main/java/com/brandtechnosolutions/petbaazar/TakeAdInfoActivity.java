package com.brandtechnosolutions.petbaazar;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TakeAdInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_ad_info);
    }


    void onOk(View view) {
        FragmentManager manager = getSupportFragmentManager();
        SellerOptionFragment dialogFragment = new SellerOptionFragment();
        dialogFragment.show(manager, "Alert");
    }
}