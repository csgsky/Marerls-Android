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
        initDebug();

        Utils.init(this);
    }

    private void initDebug() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
