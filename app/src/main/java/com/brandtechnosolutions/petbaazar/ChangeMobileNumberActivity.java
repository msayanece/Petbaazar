package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangeMobileNumberActivity extends AppCompatActivity {

    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mobile_number);
        number = (EditText) findViewById(R.id.editText6);
    }

    public void submit(View view) {
        if (view.getId() == R.id.button5) {
            String temp = number.getText().toString();
            if (temp.length() == 0) {
                Snackbar.make(view, "Hmm, looks like you forgot to enter new number!", Snackbar.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(ChangeMobileNumberActivity.this, VerifyMobileOTPActivity.class);
                intent.putExtra("mobile", temp);
                startActivity(intent);
            }
        } else if (view.getId() == R.id.button8) {
            super.onBackPressed();
        }
    }

}
