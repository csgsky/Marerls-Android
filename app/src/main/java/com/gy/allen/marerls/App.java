package com.gy.allen.marerls;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.facebook.stetho.Stetho;
import com.gy.allen.marerls.injector.components.AppComponent;
import com.gy.allen.marerls.injector.components.DaggerAppComponent;
import com.gy.allen.marerls.injector.modules.AppModule;
import com.gy.allen.marerls.util.ThemeHelper;

/**
 * Created by allen on 17/10/25.
 */

public class App extends Application  implements ThemeUtils.switchColor{
    private static App mApp;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        ThemeUtils.setSwitchColor(this);
        initAppComponent();
//        initDebug();
//        Utils.init(this);
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return mApp;
    }


    public AppComponent getAppComponent() {
        return  appComponent;
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

    @Override
    public int replaceColorById(Context context, int colorId) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return ContextCompat.getColor(context, colorId);
        }
        String theme = ThemeHelper.getThemeName(context);
        if (theme != null) {
            colorId = ThemeHelper.getThemeColorId(context, colorId, theme);
        }
        return ContextCompat.getColor(context, colorId);
    }

    @Override
    public int replaceColor(Context context, int color) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return color;
        }
        String theme = ThemeHelper.getThemeName(context);
        int colorId = -1;
        if (theme != null) {
            colorId = ThemeHelper.getThemeColor(context, color, theme);
        }
        return colorId != -1 ? ContextCompat.getColor(context, colorId) : color;
    }
}
