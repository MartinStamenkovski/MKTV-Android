package com.stamenkovski.mktv.utils.retrofit.interceptors.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by martin on 2/8/18.
 */

public class NetworkUtil {


  public static boolean isOnline(Context context) {

    ConnectivityManager connectivityManager = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = null;
    if (connectivityManager != null) {
      networkInfo = connectivityManager.getActiveNetworkInfo();
    }
    return (networkInfo != null && networkInfo.isConnected());
  }


}
