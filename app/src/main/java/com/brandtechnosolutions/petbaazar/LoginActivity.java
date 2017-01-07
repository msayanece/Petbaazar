package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


/*
* this is confidential information
* email id - msayanece@gmail.com (Sayan Mukherjee)
* Generating google app intregration with cloud
* DO NOT FORGET--- THIS MUST BE GENERATE AGAIN WHEN APP IS GOING TO BE RELEASED
* keystore path- C:\Users\user\.android\debug.keystore
* SHA1- 27:F3:65:C1:8B:5E:09:67:C9:DA:E1:8E:33:E4:73:E2:58:B4:77:05
* Client id- 932169172035-svcst09vc1im60a5nbs62agrrd5itmd7.apps.googleusercontent.com
* google cloud url- https://console.cloud.google.com/apis/credentials/wizard?api=plus-json.googleapis.com&project=petbaazar
* */
public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager mcallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mcallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginButton = (LoginButton) findViewById(R.id.facebook);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("call", "success");
            }

            @Override
            public void onCancel() {
                Log.d("call", "cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("call", "error");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_skip:
                Intent intent = new Intent(this, WebActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }
}
