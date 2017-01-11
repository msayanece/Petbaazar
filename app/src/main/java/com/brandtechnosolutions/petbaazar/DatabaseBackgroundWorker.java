package com.brandtechnosolutions.petbaazar;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
        String accountType = strings[1];
        String firstName = strings[2];                                          //get all the input data
        String lastName = strings[3];
        String email = null;
        String post_data = null;
        String login_url = "http://petbaazar.in/android/";
        if (type.equals("signUp")) {
            try {

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                if (accountType.equalsIgnoreCase("google")) {
                    email = strings[4];
                    post_data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8") + "&"
                            + URLEncoder.encode("accountType", "UTF-8") + "=" + URLEncoder.encode(accountType, "UTF-8") + "&"
                            + URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&"
                            + URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&"
                            + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                } else {
                    post_data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8") + "&"
                            + URLEncoder.encode("accountType", "UTF-8") + "=" + URLEncoder.encode(accountType, "UTF-8") + "&"
                            + URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&"
                            + URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8");
                }
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        Log.d("sayan", s);
        super.onPostExecute(s);
    }
}
