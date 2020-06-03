package com.bizitatech.android.assignment.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ak93.droid@gmail.com on 03,June,2020
 */
public class Employees implements Serializable {

    @SerializedName("Success")
    @Expose
    private List<EmployeeList> mEmployeeList = null;

    public List<EmployeeList> getmEmployeeList() {
        return mEmployeeList;
    }

    public void setmEmployeeList(List<EmployeeList> mEmployeeList) {
        this.mEmployeeList = mEmployeeList;
    }
}
