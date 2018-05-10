package com.gy.allen.marerls.injector.components;

import com.gy.allen.marerls.injector.Activity;
import com.gy.allen.marerls.injector.modules.ActivityModule;
import com.gy.allen.marerls.injector.modules.GankModule;

import dagger.Component;

/**
 * Created by allen on 18/2/26.
 */
@Activity
@Component( dependencies = AppComponent.class,
        modules = {
                ActivityModule.class,
                GankModule.class
        }
)
public interface GankComponent extends ActivityComponent {

}
