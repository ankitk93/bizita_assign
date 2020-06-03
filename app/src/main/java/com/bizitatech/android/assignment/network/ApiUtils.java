package com.bizitatech.android.assignment.network;

/**
 * Created by ak93.droid@gmail.com on 03,June,2020
 */
public class ApiUtils {

    private static final String BASE_URL = "http://aryu.co.in";

    public static ApiInterface getService(){
        return RetrofitClient.getRetrofitClient(BASE_URL).create(ApiInterface.class);
    }
}
