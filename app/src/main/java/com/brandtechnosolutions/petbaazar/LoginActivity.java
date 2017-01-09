package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


/*
* this is confidential information
* email id - msayanece@gmail.com (Sayan Mukherjee)
* Generating google app intregration with cloud
* DO NOT FORGET--- THIS MUST BE GENERATE AGAIN WHEN APP IS GOING TO BE RELEASED
* keystore path- C:,Users,user,.android,debug.keystore
* SHA1- 27:F3:65:C1:8B:5E:09:67:C9:DA:E1:8E:33:E4:73:E2:58:B4:77:05
* Client id- 932169172035-svcst09vc1im60a5nbs62agrrd5itmd7.apps.googleusercontent.com
* google cloud url- https://console.cloud.google.com/apis/credentials/wizard?api=plus-json.googleapis.com&project=petbaazar
* */
public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private LoginButton loginButton;
    private CallbackManager mcallbackManager;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
    //    private static final int FB_SIGN_IN = 0;
    private SignInButton signInButton;
    private static String TAG = "sayan";
    private FacebookCallback<LoginResult> mcallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                dispayProfileInformation(profile);
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        //initialise facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        mcallbackManager = CallbackManager.Factory.create();

        //setContentView
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //facebook button read permissions
        loginButton = (LoginButton) findViewById(R.id.facebook);
        loginButton.setReadPermissions("email", "user_birthday", "public_profile");

        //google button wide size, set onClickListener
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        // build google sign in options
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso(google sign in options).
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //track access token and profile
        AccessTokenTracker atTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        ProfileTracker pTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null)
                    dispayProfileInformation(currentProfile);
            }
        };


        // Callback registration for facebook
        loginButton.registerCallback(mcallbackManager, mcallBack);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_skip:
                Intent intent = new Intent(this, WebActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    private void updateUI(boolean isSignedIn) {
        Log.d(TAG, "GoogleUpdateUI");
        if (isSignedIn) {
//            signInButton.setVisibility(View.GONE);
//            btnSignOut.setVisibility(View.VISIBLE);
//            btnRevokeAccess.setVisibility(View.VISIBLE);
//            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            signInButton.setVisibility(View.VISIBLE);
//            btnSignOut.setVisibility(View.GONE);
//            btnRevokeAccess.setVisibility(View.GONE);
//            llProfileLayout.setVisibility(View.GONE);
        }
    }

    private void signIn() {
        Log.d(TAG, "googleSignIn");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //    private void facebookSignIn(){
//        startActivityForResult(signInIntent, FB_SIGN_IN);
//    }
    @Override
    public void onClick(View view) {
        Log.d(TAG, "googleOnClick");
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleOnConnectionFailed");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == FB_SIGN_IN){
//            Log.d(TAG,"facebook in onActivityResult");
//            mcallbackManager.onActivityResult(requestCode, resultCode, data);
//        }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "google in onActivityResult");
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Toast.makeText(this, "Sign in Successful GoogleSignInResult", Toast.LENGTH_SHORT).show();

        if (result.isSuccess()) {

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(this, "full name: " + acct.getDisplayName(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "email: " + acct.getEmail(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "last name: " + acct.getFamilyName(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "first name: " + acct.getGivenName(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "id: " + acct.getId(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "dp uri: " + acct.getPhotoUrl(), Toast.LENGTH_SHORT).show();
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    void dispayProfileInformation(Profile profile) {
        Toast.makeText(getApplicationContext(), "facebook info: ", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "first name: " + profile.getFirstName(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "last name: " + profile.getLastName(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "full name: " + profile.getName(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "link uri: " + profile.getLinkUri() + "", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "id: " + profile.getId(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "DP uri: " + profile.getProfilePictureUri(480, 720), Toast.LENGTH_LONG).show();
    }


}
