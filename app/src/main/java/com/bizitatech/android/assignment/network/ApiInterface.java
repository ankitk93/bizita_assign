package com.bizitatech.android.assignment.network;

import com.bizitatech.android.assignment.data.Employees;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by ak93.droid@gmail.com on 03,June,2020
 */
public interface ApiInterface {

    @GET("/tracking/viewreport.php")
    Call<Employees> getEmployeeList();
}
