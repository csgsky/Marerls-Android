package com.gy.allen.marerls.injector.components;

import com.gy.allen.marerls.App;
import com.gy.allen.marerls.injector.modules.AppModule;
import com.gy.allen.model.rest.ApiSp;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;


/**
 * Created by allen on 18/1/19.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    App app();

    ApiSp apiSp();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();
}
