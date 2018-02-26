package com.gy.allen.marerls.injector.modules;

import com.gy.allen.marerls.App;
import com.gy.allen.model.repository.Repository;
import com.gy.allen.model.rest.ApiSp;
import com.gy.allen.model.rest.RestDataSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 少罡 on 18/1/19.
 */

@Module
public class AppModule {
    private final App app;
    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    ApiSp provideApiSp() {
        return new ApiSp(app);
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    Repository provideDataRepository(RestDataSource restDataSource) {
        return restDataSource;
    }
}
