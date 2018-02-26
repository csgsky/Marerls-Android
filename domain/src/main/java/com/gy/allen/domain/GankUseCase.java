package com.gy.allen.domain;

import com.gy.allen.model.repository.Repository;
import com.gy.allen.model.response.DailyGankResp;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by allen on 18/2/26.
 */

public class GankUseCase extends UseCase {
    private final Repository repository;
    private final Scheduler uiThread;
    private final Scheduler executorThread;

    @Inject
    public GankUseCase(
            Repository repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread
    ) {
        this.repository = repository;
        this.uiThread = uiThread;
        this.executorThread = executorThread;
    }

    @Override
    public Observable buildObservable() {
        return null;
    }

    public Observable<DailyGankResp> dailyGank(String year, String month, String day) {
        return repository.dailyGank(year, month, day).observeOn(uiThread).subscribeOn(executorThread);
    }

}
