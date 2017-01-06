package com.brandtechnosolutions.petbaazar;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 1/6/2017.
 */


/*
* facebook petbaazar url= https://developers.facebook.com/apps/1426136754352468/settings/
* facebook quickstarts url = https://developers.facebook.com/quickstarts/1426136754352468/?platform=android
* youtube tutorial url = https://www.youtube.com/watch?v=myWu-q8Q2NA
* tutorialspoint url = http://www.tutorialspoint.com/android/android_facebook_integration.htm
* */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }

    public void printHashKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.brandtechnosolutions.petbaazar", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("Hash", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
