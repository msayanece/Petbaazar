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
    private EditText emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_take_info);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_textview_seller_info_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textView.setAdapter(adapter);
        firstNameEditText = (EditText) findViewById(R.id.seller_first_name_id);
        emailEditText = (EditText) findViewById(R.id.personal_details_email_id_editText_id);
    }

    public void continueToNextPage(View view) {
        String firstNameTemp = "", emailTemp = "";
        firstNameTemp = firstNameEditText.getText().toString();
        emailTemp = emailEditText.getText().toString();
        Intent intent = new Intent(SellerTakeInfoActivity.this, PetbaazarSellerMainActivity.class);
        intent.putExtra("first name", firstNameTemp);
        intent.putExtra("email", emailTemp);
        startActivity(intent);
    }
}
