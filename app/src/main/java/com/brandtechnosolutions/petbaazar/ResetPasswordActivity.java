package com.brandtechnosolutions.petbaazar;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email = (EditText) findViewById(R.id.editText5);
    }

    public void returnToFirstActivity(View view) {
        if (view.getId() == R.id.button6) {
//        Intent intent = new Intent(ResetPasswordActivity.this, SellProductFirstActivity.class);
//        startActivity(intent);
            String temp = email.getText().toString();
            if (temp.length() == 0) {
                Snackbar.make(view, "Oops, looks like you forgot to enter email address!", Snackbar.LENGTH_LONG).show();
            } else {
                super.onBackPressed();
            }
        } else if (view.getId() == R.id.button7) {
            super.onBackPressed();
        }

    }
}