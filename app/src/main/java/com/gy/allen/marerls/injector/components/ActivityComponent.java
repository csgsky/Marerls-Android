package com.gy.allen.marerls.injector.components;

import android.content.Context;

import com.gy.allen.marerls.injector.Activity;
import com.gy.allen.marerls.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by allen on 18/1/19.
 */

@Activity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Context context();
}
