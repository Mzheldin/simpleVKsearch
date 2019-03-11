package com.example.androidvksample.utils;

import android.os.AsyncTask;
import android.view.View;

import com.example.androidvksample.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import static com.example.androidvksample.utils.NetworkUtils.getURLResponse;

public class VKQueryTask extends AsyncTask<URL, Void, String> {

    private MainActivity mainActivity;

    public VKQueryTask(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        mainActivity.getLoadProgressBar().setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(URL... urls) {
        return getURLResponse(urls[0]);
    }

    @Override
    protected void onPostExecute(String responce) {
        if (responce != null && !responce.equals("")){
            try {
                JSONObject jsonResponce = new JSONObject(responce);
                JSONArray jsonArray = jsonResponce.getJSONArray("response");
                mainActivity.setAdapter(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
                mainActivity.showErrorTextView();
            }
            mainActivity.showResultTextView();
        } else mainActivity.showErrorTextView();
        mainActivity.getLoadProgressBar().setVisibility(View.INVISIBLE);
    }
}
