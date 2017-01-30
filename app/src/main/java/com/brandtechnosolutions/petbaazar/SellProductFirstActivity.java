package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SellProductFirstActivity extends AppCompatActivity {

    private EditText emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_product_first);
        emailEditText = (EditText) findViewById(R.id.seller_email_edit_text_id);
    }

    public void sellerLogin(View view) {
//        Snackbar.make(view, "This part is under development!", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
        String tempEmail = emailEditText.getText().toString();
        Intent intent = new Intent(SellProductFirstActivity.this, PetbaazarSellerMainActivity.class);
        intent.putExtra("email", tempEmail);
        startActivity(intent);
    }

    public void createSellerAccount(View view) {
        Intent intent = new Intent(SellProductFirstActivity.this, SellerTypeEntryActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(SellProductFirstActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
//        Snackbar.make(view, "This part is under development!", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
    }
}
