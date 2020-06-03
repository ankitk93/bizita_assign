package com.bizitatech.android.assignment.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bizitatech.android.assignment.R;

/**
 * Created by ak93.droid@gmail.com on 03,June,2020
 */
public class Utility {

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static Toolbar toolBarFunction(final Activity activity, int titleVisibility, String title, int iconVisibility, int icon){
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView toolbarText = activity.findViewById(R.id.toolbar_homeTitle);
        toolbarText.setVisibility(titleVisibility);
        if (title != null) {
            toolbarText.setText(title);
        }

        ImageView toolbar_image = activity.findViewById(R.id.toolbar_image);
        toolbar_image.setVisibility(iconVisibility);
        toolbar_image.setImageResource(icon);
        toolbar_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

        return toolbar;
    }

    public static Dialog showProgressDialog(Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_progress_bar);
        dialog.setCancelable(false);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }
}
