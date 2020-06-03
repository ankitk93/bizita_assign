package com.bizitatech.android.assignment.employeeDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bizitatech.android.assignment.R;
import com.bizitatech.android.assignment.data.EmployeeList;
import com.bizitatech.android.assignment.employeeList.MainActivity;
import com.bizitatech.android.assignment.utils.Utility;
import com.bumptech.glide.Glide;

public class EmployeeDetailsScreen extends AppCompatActivity {

    private ImageView mImageViewEmployee;
    private TextView mTextViewName, mTextViewId, mTextViewEmployeeCode, mTextViewTeamName,
            mTextViewTeamId, mTextViewAddress, mTextViewDescription, mTextViewContactNumber;

    private String mImageUrl;

    private EmployeeList mEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details_screen);

        setSupportActionBar(Utility.toolBarFunction(EmployeeDetailsScreen.this, View.VISIBLE,
                "Details", View.VISIBLE, R.drawable.left_arrow));

        Bundle mEmployeeBundle =getIntent().getExtras();
        if (mEmployeeBundle != null){
            mEmployee = (EmployeeList) mEmployeeBundle.getSerializable("employeeData");
        }

        initViews();

        setUpDataToView(mEmployee);
    }

    private void initViews(){
        mImageViewEmployee = findViewById(R.id.iv_profile_pic_detail);
        mTextViewName = findViewById(R.id.tv_employee_name_detail);
        mTextViewId = findViewById(R.id.tv_id);
        mTextViewEmployeeCode = findViewById(R.id.tv_employee_code);
        mTextViewTeamName = findViewById(R.id.tv_team_name);
        mTextViewTeamId = findViewById(R.id.tv_team_id);
        mTextViewAddress = findViewById(R.id.tv_address);
        mTextViewDescription = findViewById(R.id.tv_description);
        mTextViewContactNumber = findViewById(R.id.tv_contact_number);
    }

    private void setUpDataToView(EmployeeList employeeData){
        mTextViewName.setText(employeeData.getName());
        mTextViewId.setText(employeeData.getId());
        mTextViewEmployeeCode.setText(employeeData.getEmpcode());
        mTextViewTeamName.setText(employeeData.getCategory());
        mTextViewTeamId.setText(employeeData.getCategoryid());
        mTextViewAddress.setText(employeeData.getAddress());
        mTextViewDescription.setText(employeeData.getDescription());
        mTextViewContactNumber.setText(employeeData.getContact());

        mImageUrl = employeeData.getImage();

        if (mImageUrl != null){
            Glide.with(this)
                    .load(mImageUrl)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
                    .into(mImageViewEmployee);
        }else {
            Glide.with(this)
                    .load(R.drawable.ic_broken_image)
                    .into(mImageViewEmployee);
        }
    }
}
