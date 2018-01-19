package com.gy.allen.marerls.injector.components;

import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.injector.Activity;
import com.gy.allen.marerls.injector.modules.ActivityModule;
import com.gy.allen.marerls.injector.modules.ThreatersModule;

import dagger.Component;

/**
 * Created by allen on 18/1/19.
 */
@Activity
@Component(dependencies = AppComponent.class,
    modules = {
            ActivityModule.class,
            ThreatersModule.class
    }
)

public interface MoviceComponent extends ActivityComponent {
    void inject(MainActivity activity);
}
