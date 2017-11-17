package com.gy.allen.marerls;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.gy.allen.marerls.util.Utils;

/**
 * Created by allen on 17/10/25.
 */

public class App extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Stetho.initializeWithDefaults(this);
        Utils.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
