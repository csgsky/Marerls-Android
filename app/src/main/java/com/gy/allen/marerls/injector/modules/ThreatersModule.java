package com.gy.allen.marerls.injector.modules;

import com.gy.allen.domain.ThreatersUseCase;
import com.gy.allen.marerls.injector.Activity;
import com.gy.allen.model.repository.Repository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by allen on 18/1/19.
 */

@Module
public class ThreatersModule {
    public ThreatersModule() {}
    @Provides
    @Activity
    ThreatersUseCase provideThreatersUseCase(
            Repository repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread
            ) {
        return new ThreatersUseCase(repository,  uiThread, executorThread);
    }
}
