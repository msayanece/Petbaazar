package com.brandtechnosolutions.petbaazar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    public void returnToFirstActivity(View view) {
//        Intent intent = new Intent(ResetPasswordActivity.this, SellProductFirstActivity.class);
//        startActivity(intent);
        super.onBackPressed();
    }
}