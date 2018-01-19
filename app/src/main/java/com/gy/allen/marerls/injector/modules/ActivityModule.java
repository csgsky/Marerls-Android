package com.gy.allen.marerls.injector.modules;

import android.content.Context;


import com.gy.allen.marerls.injector.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allen on 18/1/19.
 */

@Module
public class ActivityModule {
    private final Context context;
    public ActivityModule(Context context) {
        this.context = context;
    }
    @Provides @Activity
    Context provideActivityContext() {
        return context;
    }
}
