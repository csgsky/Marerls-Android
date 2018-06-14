package com.gy.allen.marerls.base;

public interface IPresenter<T extends IBaseView> {
    void attachView(T view);
    void detachView();
}
