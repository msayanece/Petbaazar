package com.brandtechnosolutions.petbaazar;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PetbaazarSellerMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petbaazar_seller_main);
//
//        SellerHomeFragment Fragment = new SellerHomeFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container_id, Fragment);
//        transaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.petbaazar_seller_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent intent = new Intent(PetbaazarSellerMainActivity.this, SellProductFirstActivity.class);
//            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentSuper fragment = null;
        FragmentTransaction transaction = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                fragment = new SellerHomeFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, fragment);
                transaction.commit();
                break;
            case R.id.nav_orders:
                fragment = new SellerOrderFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, fragment);
                transaction.commit();
                break;
            case R.id.nav_catalogue:
                fragment = new SellerCatalogueFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, fragment);
                transaction.commit();
                break;
            case R.id.nav_payments:
                fragment = new SellerPaymentsFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, fragment);
                transaction.commit();
                break;
            case R.id.nav_returns:
                fragment = new SellerReturnsFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, fragment);
                transaction.commit();
                break;
            case R.id.nav_setting:
                fragment = new SellerSettingsFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, fragment);
                transaction.commit();
                break;
            case R.id.nav_add:
                fragment = new SellerAddProductFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_id, fragment);
                transaction.commit();
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
