package com.brandtechnosolutions.petbaazar;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by user on 1/10/2017.
 */

public class DatabaseBackgroundWorker extends AsyncTask<String, Void, String> {

    private Context context;
    DatabaseBackgroundWorker(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
