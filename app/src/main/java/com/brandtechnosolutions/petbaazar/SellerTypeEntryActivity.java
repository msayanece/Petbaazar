package com.brandtechnosolutions.petbaazar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SellerTypeEntryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
