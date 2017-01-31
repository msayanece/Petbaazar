package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class SellerTakeInfoActivity extends AppCompatActivity {

    private static final String[] COUNTRIES = new CountyDetails().getCountry();
    private EditText firstNameEditText, emailEditText, password, confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_take_info);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_textview_seller_info_id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textView.setAdapter(adapter);
        password = (EditText) findViewById(R.id.personal_details_enter_password_editText_id);
        confirmPassword = (EditText) findViewById(R.id.personal_details_confirm_password_editText_id);
        firstNameEditText = (EditText) findViewById(R.id.seller_first_name_id);
        emailEditText = (EditText) findViewById(R.id.personal_details_email_id_editText_id);
    }

    public void continueToNextPage(View view) {
        String firstNameTemp = "", emailTemp = "", passTemp = "", conPassTemp = "";
        firstNameTemp = firstNameEditText.getText().toString();
        emailTemp = emailEditText.getText().toString();
        passTemp = password.getText().toString();
        conPassTemp = confirmPassword.getText().toString();
        Intent intent = new Intent(SellerTakeInfoActivity.this, PetbaazarSellerMainActivity.class);
        intent.putExtra("first name", firstNameTemp);
        intent.putExtra("email", emailTemp);
        if (passTemp.equals(conPassTemp)) {
            intent.putExtra("password", passTemp);
            startActivity(intent);
        } else {
            Snackbar.make(view, "Password does not match! Try again", Snackbar.LENGTH_LONG).show();
            password.setText("");
            confirmPassword.setText("");
            password.requestFocus();
        }
    }
}
