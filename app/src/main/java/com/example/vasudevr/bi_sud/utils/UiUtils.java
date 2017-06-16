package com.example.vasudevr.bi_sud.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;

/**
 * Created by vasudevr on 6/16/2017.
 */

public class UiUtils {

    public static boolean checkInternetConnection(Activity context) {
        return isNetworkConnected(context);
    }

    public static boolean isNetworkConnected(Activity ctx) {
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo mobileInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi_info = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if ((mobileInfo != null && mobileInfo.getState() == NetworkInfo.State.CONNECTED)
                || wifi_info != null
                && wifi_info.getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else {
            connected = false;

        }
        return connected;
    }

    public static void showErrorAlert(String title, String message, final Activity ctx, final int flag) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        LayoutInflater mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = mInflater.inflate(R.layout.app_error_popup, new LinearLayout(ctx), false);
        builder.setView(dialogView);
        TextView textViewOrderNo = (TextView) dialogView.findViewById(R.id.error_popup_title);
        textViewOrderNo.setText(title);
        TextView textViewOrder = (TextView) dialogView.findViewById(R.id.textViewErrorMessage);
        textViewOrder.setText(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (flag == 1) {
                    ctx.finish();
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }
        });
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
            }

        });
        AlertDialog mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

}
