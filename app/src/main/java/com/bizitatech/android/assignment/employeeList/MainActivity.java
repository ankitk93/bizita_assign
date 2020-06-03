package com.bizitatech.android.assignment.employeeList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bizitatech.android.assignment.R;
import com.bizitatech.android.assignment.data.EmployeeList;
import com.bizitatech.android.assignment.data.Employees;
import com.bizitatech.android.assignment.network.ApiUtils;
import com.bizitatech.android.assignment.utils.NoInternetConnection;
import com.bizitatech.android.assignment.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewEmployee;
    private EmployeeAdapter mAdapterEmployee;
    private List<EmployeeList> mList;
    private Dialog mCustomProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //views initialization
        initViews();

        //api call
        getDataFromApi();
    }

    private void initViews(){
        setSupportActionBar(Utility.toolBarFunction(MainActivity.this, View.VISIBLE, "Employees", View.GONE, 0));
        mCustomProgressDialog = Utility.showProgressDialog(this);

        mRecyclerViewEmployee = findViewById(R.id.rv_employees);
        mRecyclerViewEmployee.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerViewEmployee.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void getDataFromApi(){
        mList = new ArrayList<>();
        if (Utility.checkInternetConnection(this)){
            mCustomProgressDialog.show();
            ApiUtils.getService().getEmployeeList().enqueue(new Callback<Employees>() {
                @Override
                public void onResponse(Call<Employees> call, Response<Employees> response) {
                    if (response.isSuccessful()){
                        mCustomProgressDialog.dismiss();
                        Employees mEmployees = response.body();
                        if (mEmployees != null){
                            mList = mEmployees.getmEmployeeList();
                            setAdapterData(mList);
                        }else {
                            mCustomProgressDialog.dismiss();
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.no_employee), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        mCustomProgressDialog.dismiss();
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Employees> call, Throwable t) {
                    mCustomProgressDialog.dismiss();
                    Log.e("responseFrom", "Error : " + t.getMessage());
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            startActivity(new Intent(MainActivity.this, NoInternetConnection.class));
        }
    }

    private void setAdapterData(List<EmployeeList> mList){
        mAdapterEmployee = new EmployeeAdapter(mList, this);
        mRecyclerViewEmployee.setAdapter(mAdapterEmployee);
    }
}
