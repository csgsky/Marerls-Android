package com.gy.allen.marerls.injector.modules;

import android.app.Activity;
import android.content.Context;

import com.gy.allen.marerls.injector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;


/**
 * Created by allen on 18/1/19.
 */

@Module
public class ActivityModule {
    private final Activity mActivity;
    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides @PerActivity
    public Context provideActivityContext() {
        return mActivity;
    }
}
