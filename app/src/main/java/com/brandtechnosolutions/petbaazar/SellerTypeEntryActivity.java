package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class SellerTypeEntryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String[] COUNTRIES = new CountyDetails().getCountry();
    Spinner spinner1, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_type_entry);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.business_type, android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerAdapter);
        spinner2.setOnItemSelectedListener(this);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.location_edit_text_auto_textview_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void toSellerFullInfo(View view) {
        Intent intent = new Intent(SellerTypeEntryActivity.this, SellerTakeInfoActivity.class);
        startActivity(intent);
    }
}