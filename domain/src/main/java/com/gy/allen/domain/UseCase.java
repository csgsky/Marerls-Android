package com.gy.allen.domain;


import io.reactivex.Observable;

/**
 * Created by allen on 18/1/19.
 */

public abstract class UseCase<T> {
    public abstract Observable<T> buildObservable();

    public Observable<T> execute() {
        return buildObservable();
    }
}
