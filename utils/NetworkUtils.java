package com.example.androidvksample.utils;

import android.net.Uri;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static URL getURL(String userId){
        Uri buildUrl = Uri.parse(URLFields.VK_API_BASE_URL.getValue() + URLFields.VK_USERS_GET.getValue())
                .buildUpon()
                .appendQueryParameter(URLFields.PARAM_USER_ID.getValue(), userId)
                .appendQueryParameter(URLFields.PARAM_FIELDS.getValue(),
                                URLFields.ABOUT.getValue() + "," +
                                URLFields.BDATE.getValue() + "," +
                                URLFields.HOME_TOWN.getValue() + "," +
                                URLFields.INTERESTS.getValue() + "," +
                                URLFields.ONLINE.getValue())
                .appendQueryParameter(URLFields.PARAM_VERSION.getValue(), URLFields.VERSION.getValue())
                .appendQueryParameter(URLFields.PARAM_ACCESS_TOKEN.getValue(), URLFields.ACCESS_TOKEN.getValue())
                .build();
        System.out.println("FFFFFFFFUUUUUUUUUUUU" + buildUrl.toString());
        URL url = null;
        try {
            url = new URL(buildUrl.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }



    static String getURLResponse(URL url){
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try (Scanner scanner = new Scanner(urlConnection.getInputStream())) {
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) return scanner.next();
                else return null;
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
