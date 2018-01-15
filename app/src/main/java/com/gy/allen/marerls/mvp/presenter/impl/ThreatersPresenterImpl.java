package com.gy.allen.marerls.mvp.presenter.impl;

import com.gy.allen.marerls.data.ThreatersResponse;
import com.gy.allen.marerls.mvp.BasePresenter;
import com.gy.allen.marerls.mvp.presenter.ThreatersPresenter;
import com.gy.allen.marerls.retrofit.RetrofitDoubanClient;
import com.gy.allen.marerls.retrofit.RetrofitDoubanService;
import com.gy.allen.marerls.ui.fragment.GankFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by allen on 18/1/15.
 */

public class ThreatersPresenterImpl implements ThreatersPresenter, BasePresenter {
    private final CompositeDisposable compositeDisposable;

    private final GankFragment mGankFragment;

    public ThreatersPresenterImpl(GankFragment mGankFragment, CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        this.mGankFragment = mGankFragment;
    }


    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void subscribeThreater(int start) {
        RetrofitDoubanClient.getInstance()
                .create(RetrofitDoubanService.class)
                .getThreaters(start+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ThreatersResponse>() {
                    @Override
                    public void onNext(ThreatersResponse value) {
                        mGankFragment.setThreatersList(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
//                        Toast.makeText(mGankFragment.getActivity(), "complete", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void subscribeMoreThreaterMore(int start) {

    }
}
