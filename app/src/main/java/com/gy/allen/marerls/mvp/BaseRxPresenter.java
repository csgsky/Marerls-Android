package com.gy.allen.marerls.mvp;

import com.gy.allen.marerls.base.IBaseView;
import com.gy.allen.marerls.base.IPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseRxPresenter<T extends IBaseView> implements IPresenter<T>  {
    private T mView;

    private CompositeDisposable mCompositeDisposable;
    public BaseRxPresenter () {

    }

    protected void addDisposable(Disposable disposable) {
        if (null == mCompositeDisposable) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    public T getView() {
        return mView;
    }

    protected void dispose() {
        if (null != mCompositeDisposable) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void detachView() {
        mView = null;
        mCompositeDisposable.dispose();
    }
}
