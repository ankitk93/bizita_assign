package com.bizitatech.android.assignment.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bizitatech.android.assignment.R;
import com.bizitatech.android.assignment.employeeList.MainActivity;

public class NoInternetConnection extends AppCompatActivity {

    private Button mButtonNetworkCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);

        mButtonNetworkCheck = findViewById(R.id.btn_network_check);

        mButtonNetworkCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.checkInternetConnection(NoInternetConnection.this)){
                    startActivity(new Intent(NoInternetConnection.this, MainActivity.class));
                }else {
                    Toast.makeText(NoInternetConnection.this, "Please check your connection again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
