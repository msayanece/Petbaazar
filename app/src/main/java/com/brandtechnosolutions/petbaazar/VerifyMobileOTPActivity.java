package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class VerifyMobileOTPActivity extends AppCompatActivity {

    private TextView mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_mobile_otp);
        mobile = (TextView) findViewById(R.id.textView18);
        mobile.setText(getIntent().getStringExtra("mobile"));
    }

    public void verify(View view) {
        Intent intent = new Intent(VerifyMobileOTPActivity.this, OptionActivity.class);
        startActivity(intent);
    }

}
