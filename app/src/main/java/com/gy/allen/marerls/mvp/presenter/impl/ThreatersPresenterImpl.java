package com.gy.allen.marerls.mvp.presenter.impl;

import com.gy.allen.marerls.mvp.BasePresenter;
import com.gy.allen.marerls.mvp.presenter.ThreatersPresenter;
import com.gy.allen.marerls.ui.fragment.GankFragment;

import io.reactivex.disposables.CompositeDisposable;

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

    }

    @Override
    public void subscribeMoreThreaterMore(int start) {

    }
}
