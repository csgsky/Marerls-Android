package com.gy.allen.marerls.mvp.presenter.impl;

import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.data.GankTypeBean;
import com.gy.allen.marerls.mvp.BasePresenter;
import com.gy.allen.marerls.mvp.presenter.SearchPresenter;
import com.gy.allen.marerls.retrofit.RetrofitClient;
import com.gy.allen.marerls.retrofit.RetrofitService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by allen on 17/10/25.
 */

public final class SearchPresenterImpl implements SearchPresenter, BasePresenter {

    private final CompositeDisposable compositeDisposable;

    private final MainActivity mMainActivity;

    public SearchPresenterImpl(CompositeDisposable compositeDisposable, MainActivity mainA){
        this.compositeDisposable = compositeDisposable;
        this.mMainActivity = mainA;
    }


    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void subscribeSearch(int count, int page) {
        RetrofitClient.getInstance()
                .create(RetrofitService.class)
                .getSearchResult(count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<GankTypeBean>() {
                    @Override
                    public void onNext(GankTypeBean value) {
                        mMainActivity.setSearchInfo(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMainActivity.showNetworkError();
                    }

                    @Override
                    public void onComplete() {
                        compositeDisposable.clear();
                    }
                });
    }

    @Override
    public void getMoreData(int count, int page) {

    }
}
