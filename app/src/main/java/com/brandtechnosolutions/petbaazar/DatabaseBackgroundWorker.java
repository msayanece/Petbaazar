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

        String type = strings[0];
        String firstName = strings[1];                                          //get all the input data
        String lastName = strings[2];
        String accountId = strings[3];
        String photoUrl = strings[4];
        String email = null;
        if (strings[5]!= null){
            email = strings[5];
        }


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
