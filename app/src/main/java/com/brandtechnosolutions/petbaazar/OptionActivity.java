package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class OptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton find = (FloatingActionButton) findViewById(R.id.sell_menu_id);
        FloatingActionButton post = (FloatingActionButton) findViewById(R.id.post_ad_menu_id);
        FloatingActionButton sell = (FloatingActionButton) findViewById(R.id.buy_menu_id);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please mention category!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(OptionActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, SellerTypeEntryActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Please select options!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, TakeAdInfoActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Want to capture?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
