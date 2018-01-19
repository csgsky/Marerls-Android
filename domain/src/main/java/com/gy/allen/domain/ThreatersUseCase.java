package com.gy.allen.domain;

import com.gy.allen.model.repository.Repository;
import com.gy.allen.model.response.ThreatersResp;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by allen on 18/1/19.
 */

public class ThreatersUseCase extends UseCase {
    private final Repository repository;
    private final Scheduler uiThread;
    private final Scheduler executorThread;

    @Inject
    public ThreatersUseCase(
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

    public Observable<ThreatersResp> getThreatorList(String start) {
        return repository.getThreatersList(start).subscribeOn(executorThread).observeOn(uiThread);
    }
}
