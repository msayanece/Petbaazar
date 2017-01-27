/*
* version 1.1
* */


package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

// activity for handling webpage
public class WebActivity extends AppCompatActivity {

    private static String TAG = "sayan";                          //for log output
    WebView view;
    private ProgressBar progress;
    private String url;                                    //home url

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setProgress(0);
        progress.setMax(100);
        view = (WebView) findViewById(R.id.web);
        view.setWebViewClient(new MyWebViewClient());
        view.setWebChromeClient(new WebChromeClient(){             //Anonymous inner class
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress < 100 && progress.getVisibility() == ProgressBar.GONE){
                    progress.setVisibility(ProgressBar.VISIBLE);
                }

                progress.setProgress(newProgress);
                if(newProgress == 100) {
                    progress.setVisibility(ProgressBar.GONE);
                }
            }
        });
        WebSettings settings = view.getSettings();                //settings update
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (savedInstanceState!= null) {                          //reuse saved instance state
            Bundle bundle = savedInstanceState.getBundle("state");
            view.restoreState(bundle);
        }else{
            view.loadUrl(url);                                    //given url loading
        }
    }

    @Override                                                    //back to previous page or exit
    public void onBackPressed() {
        if (view.canGoBack()){
            view.goBack();
        }else
            super.onBackPressed();
    }

    @Override                                                    //save the instance state
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Bundle state=new Bundle();
        view.saveState(state);                                  //save page state to bundle
        outState.putBundle("state",state);                      //(key, value) pair
    }

    // inner class for loading url on the app by user input touch
    private class MyWebViewClient extends WebViewClient {

        @Override                                              //for android version before lollipop
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)   //for newer android version from lollipop
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override                                       //after page load complete, hide progressbar
        public void onPageFinished(WebView view, String url) {
            if (progress.getWindowVisibility()==progress.VISIBLE) {
                progress.setVisibility(progress.INVISIBLE);
            }
        }
    }
}