package com.gy.allen.marerls.injector.components;

import android.app.Activity;
import android.content.Context;

import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.injector.scope.PerActivity;
import com.gy.allen.marerls.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by allen on 18/1/19.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Context context();

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
