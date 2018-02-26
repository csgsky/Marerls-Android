package com.gy.allen.marerls.injector.modules;

import com.gy.allen.domain.GankUseCase;
import com.gy.allen.marerls.injector.Activity;
import com.gy.allen.model.repository.Repository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by allen on 18/2/26.
 */
@Module
public class GankModule {
    public GankModule() {}

    @Provides
    @Activity
    GankUseCase provideGankUseCase(
            Repository repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread
    ){
        return new GankUseCase(repository, uiThread, executorThread);
    }
}
