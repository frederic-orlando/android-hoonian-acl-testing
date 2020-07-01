package com.example.testmvp.presenter.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class UtilsCon {
    @RootContext
    protected Context context;
    public boolean isInternetAvailable(Context context) {
        return isUsingEthernet()||isUsingWiFi() || isUsingMobileData();
    }

    private boolean isUsingMobileData() {
        ConnectivityManager connectivity = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo mobileInfo = connectivity
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return mobileInfo != null
                && (mobileInfo.getState() == NetworkInfo.State.CONNECTED
                || mobileInfo.getState() == NetworkInfo.State.CONNECTING);
    }

    private  boolean isUsingWiFi() {
        ConnectivityManager connectivity = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo = connectivity
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return wifiInfo != null
                && (wifiInfo.getState() == NetworkInfo.State.CONNECTED
                || wifiInfo.getState() == NetworkInfo.State.CONNECTING);

    }

    private boolean isUsingEthernet() {
        ConnectivityManager connectivity = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo = connectivity
                .getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);

        return wifiInfo != null
                && (wifiInfo.getState() == NetworkInfo.State.CONNECTED
                || wifiInfo.getState() == NetworkInfo.State.CONNECTING);

    }

}
