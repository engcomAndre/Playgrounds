package com.andre.tasks.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.net.ConnectivityManagerCompat;

public class NetworkUtils {
    public static boolean isConnectionAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
