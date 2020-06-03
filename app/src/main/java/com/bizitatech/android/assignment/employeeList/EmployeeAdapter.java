package com.bizitatech.android.assignment.employeeList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bizitatech.android.assignment.R;
import com.bizitatech.android.assignment.data.EmployeeList;
import com.bizitatech.android.assignment.employeeDetails.EmployeeDetailsScreen;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ak93.droid@gmail.com on 03,June,2020
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<EmployeeList> mEmployeeList;
    private Context mContext;

    public EmployeeAdapter(List<EmployeeList> mEmployeeList, Context mContext){
        this.mContext = mContext;
        this.mEmployeeList = mEmployeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_employee, parent, false);
        return new EmployeeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final EmployeeList employee = mEmployeeList.get(position);

        holder.mEmployeeName.setText(employee.getName());
        holder.mEmployeeId.setText(employee.getEmpcode());

        String imageUrl = employee.getImage();

        if (imageUrl != null){
            Glide.with(mContext)
                    .load(employee.getImage())
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
                    .into(holder.mImageView);
        }else {
            Glide.with(mContext)
                    .load(R.drawable.ic_broken_image)
                    .into(holder.mImageView);
        }

        holder.mLinearLayoutSingleEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, EmployeeDetailsScreen.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("employeeData", employee);
                mIntent.putExtras(mBundle);
                mContext.startActivity(mIntent);
            }
        });
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView mEmployeeName, mEmployeeId;
        ImageView mImageView;
        LinearLayout mLinearLayoutSingleEmployee;
        EmployeeViewHolder(View view){
            super(view);

            mImageView = view.findViewById(R.id.iv_profile_pic);
            mEmployeeName = view.findViewById(R.id.tv_employee_name);
            mEmployeeId = view.findViewById(R.id.tv_employee_id);

            mLinearLayoutSingleEmployee = view.findViewById(R.id.ll_single_employee);
        }
    }


    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }
}
