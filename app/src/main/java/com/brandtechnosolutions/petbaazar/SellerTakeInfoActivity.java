package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class SellerTakeInfoActivity extends AppCompatActivity {

    private static final String[] COUNTRIES = new CountyDetails().getCountry();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_take_info);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_textview_seller_info_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textView.setAdapter(adapter);
    }

    public void continueToNextPage(View view) {
        Intent intent = new Intent(SellerTakeInfoActivity.this, SellerProductDetailsActivity.class);
        startActivity(intent);
    }
}
