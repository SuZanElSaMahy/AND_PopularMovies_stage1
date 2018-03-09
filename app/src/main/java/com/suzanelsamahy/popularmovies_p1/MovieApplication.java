package com.suzanelsamahy.popularmovies_p1;

import android.app.Application;
import android.content.Context;

import com.suzanelsamahy.popularmovies_p1.Utilities.ConnectivityReceiver;

/**
 * Created by suzanelsamahy on 3/6/18.
 */

public class MovieApplication extends Application {

    private static MovieApplication mInstance;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

    }

    public static synchronized MovieApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }



}
