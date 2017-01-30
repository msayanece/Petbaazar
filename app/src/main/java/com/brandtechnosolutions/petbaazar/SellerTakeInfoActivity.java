package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class SellerTakeInfoActivity extends AppCompatActivity {

    private static final String[] COUNTRIES = new CountyDetails().getCountry();
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_take_info);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_textview_seller_info_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textView.setAdapter(adapter);
        firstNameEditText = (EditText) findViewById(R.id.seller_first_name_id);
        lastNameEditText = (EditText) findViewById(R.id.seller_last_name_id);
    }

    public void continueToNextPage(View view) {
        String firstNameTemp, lastNameTemp;
        firstNameTemp = firstNameEditText.getText().toString();
        lastNameTemp = lastNameEditText.getText().toString();
        Intent intent = new Intent(SellerTakeInfoActivity.this, PetbaazarSellerMainActivity.class);
        intent.putExtra("first name", firstNameTemp);
        intent.putExtra("last name", lastNameTemp);
        startActivity(intent);
    }
}
