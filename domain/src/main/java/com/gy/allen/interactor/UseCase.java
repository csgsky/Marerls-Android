package com.gy.allen.interactor;

import com.gy.allen.executor.PostExecutionThread;
import com.gy.allen.executor.ThreadExecutor;
import com.gy.allen.utils.Preconditions;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * desc 不同用例的抽象类，可指定业务逻辑
 * @param <T>
 * @param <Params>
 */
public abstract class UseCase<T, Params> {
    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;

    UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
    }

    /**
     * 设置线程调度
     */
    public Disposable execute(DisposableObserver<T> observer, Params params) {
        Preconditions.checkNotNull(observer);
        Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler());
        return observable.subscribeWith(observer);
    }

    /**
     * 不进行线程调度
     */
    public Observable<T> buildObservable(Params params) {
        return this.buildUseCaseObservable(params);
    }

    abstract Observable<T> buildUseCaseObservable(Params params);
}

