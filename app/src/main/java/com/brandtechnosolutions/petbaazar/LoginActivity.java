package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.net.Uri;
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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
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

import org.json.JSONException;
import org.json.JSONObject;


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

    private static final int RC_SIGN_IN = 007;
    private static String TAG = "sayan";
    AccessTokenTracker atTracker;
    private ProfileTracker pTracker;
    private LoginButton loginButton;
    private CallbackManager mcallbackManager;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;
    private String email;
    private FacebookCallback<LoginResult> mcallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                putFacebookProfileInformation(profile);
            }
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            Log.v("LoginActivity", response.toString());
                            // Application code
                            try {
                                email = object.getString("email");
//                                sendEmail();
//                                String birthday = object.getString("birthday");
//                                Toast.makeText(getApplicationContext(), "email: " + email + " and birthday: " + birthday, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender,birthday");
            request.setParameters(parameters);
            request.executeAsync();
            callBuyerSellerActivity();
            Toast.makeText(getApplicationContext(), "Log in Successful!", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "Log in Cancel!", Toast.LENGTH_LONG).show();

        }
        @Override
        public void onError(FacebookException error) {
            Toast.makeText(getApplicationContext(), "Something went wrong, Log in Failed!", Toast.LENGTH_LONG).show();
        }
    };


    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        FacebookSdk.sdkInitialize(getApplicationContext());
        mcallbackManager = CallbackManager.Factory.create();
        //setContentView
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
                                                                                         //facebook button read permissions
        loginButton = (LoginButton) findViewById(R.id.facebook);
        loginButton.setReadPermissions("email", "public_profile");
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
        atTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };
        pTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null)
                    putFacebookProfileInformation(currentProfile);
            }
        };

//        atTracker.startTracking();
//        pTracker.startTracking();
                                                                                    // Callback registration for facebook
        loginButton.registerCallback(mcallbackManager, mcallBack);
    }


    //onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_log_in, menu);
        return true;
    }


    //onOptionsItemSelected
    @Override                                                                       //for skipping to webview
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_skip:
                startWebActivity();
                return true;
            default:
                return true;
        }
    }
//    private void updateUI(boolean isSignedIn) {                                       //only if update method needed
//        Log.d(TAG, "GoogleUpdateUI");
//        if (isSignedIn) {
//        } else {
//        }
//    }


    //onClick
    @Override
    public void onClick(View view) {
        Log.d(TAG, "googleOnClick");
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }


    //onConnectionFailed
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleOnConnectionFailed");
    }


    //onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "google in onActivityResult");
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    //signIn google
    private void signIn() {
        Log.d(TAG, "googleSignIn");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    //handleSignInResult
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            Toast.makeText(this, "Sign in Successful!", Toast.LENGTH_SHORT).show();
                                                                                        // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            putGoogleAccountInformation(acct);
            callBuyerSellerActivity();
//            updateUI(true);
        } else {
            Toast.makeText(this, "Something went wrong, sign in failed!", Toast.LENGTH_SHORT).show();
                                                                                        // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

    void sendEmail() {
        Intent chooser = null;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        String[] recipient = {email};
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipient);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Auto Generated from App");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Sayan, \n  Hope you are doing well. \nThis is an autogenerated mail. Don not reply! \n regards, \n Petbazar");
        emailIntent.setType("messege/rfc822");
        chooser = Intent.createChooser(emailIntent, "Send Email");
        startActivity(chooser);
    }

    //putGoogleAccountInformation to database                                                                                        //put facebook profile information to Database
    private void putGoogleAccountInformation(GoogleSignInAccount acct) {
        String type = "signUp";
        String accountType = "google";
        String email = acct.getEmail();
        String lastName = acct.getFamilyName();
        String firstName = acct.getGivenName();
        DatabaseBackgroundWorker worker = new DatabaseBackgroundWorker(getApplicationContext());
        worker.execute(type, accountType, firstName, lastName, email);
    }


    //putFacebookProfileInformation on database                                                                                        //put facebook profile information to Database
    private void putFacebookProfileInformation(Profile profile) {
        String type = "signUp";
        String accountType = "facebook";
        String firstName = profile.getFirstName();
        String lastName = profile.getLastName();
        DatabaseBackgroundWorker worker = new DatabaseBackgroundWorker(getApplicationContext());
        worker.execute(type, accountType, firstName, lastName, email);
    }

    public void callNextActivity(View view) {
        if (view.getId() == R.id.button_login) {
            callBuyerSellerActivity();
        }
    }

    void callBuyerSellerActivity() {
        Intent intent = new Intent(this, OptionActivity.class);
        startActivity(intent);
    }

    void startWebActivity() {
        Intent intent = new Intent(LoginActivity.this, WebActivity.class);
        startActivity(intent);
    }
//    @Override                                                                         //if ProfileTracker.startTracking() and AccessTokenTracker.startTracking() called
//    protected void onStop() {
//        super.onStop();
//        atTracker.stopTracking();
//        pTracker.stopTracking();
//    }
}
