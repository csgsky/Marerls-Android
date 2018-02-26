package com.gy.allen.marerls.mvp.presenter.impl;

import com.gy.allen.marerls.data.GankMeiZiBean;
import com.gy.allen.marerls.mvp.BasePresenter;
import com.gy.allen.marerls.mvp.presenter.MeiZiPresenter;
import com.gy.allen.marerls.retrofit.RetrofitClient;
import com.gy.allen.marerls.retrofit.RetrofitService;
import com.gy.allen.marerls.ui.fragment.MeiZiFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by allen on 17/10/29.
 */

public class MeiZiPresenterImpl implements MeiZiPresenter, BasePresenter {
    private final CompositeDisposable compositeDisposable;

    private final MeiZiFragment mMeiZiFragment;

    public MeiZiPresenterImpl(MeiZiFragment mMeiZiFragment, CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        this.mMeiZiFragment = mMeiZiFragment;
    }

    @Override
    public void subscribeMeiZi(int page) {
        RetrofitClient.getInstance()
                .create(RetrofitService.class)
                .getGankMeiZiList(page + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<GankMeiZiBean>() {
                    @Override
                    public void onNext(GankMeiZiBean value) {
                        mMeiZiFragment.setMeiZiInfo(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMeiZiFragment.showLoadError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
