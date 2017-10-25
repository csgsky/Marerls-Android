package com.gy.allen.marerls.mvp.presenter;

/**
 * Created by allen on 17/10/25.
 */

public interface SearchPresenter {
    void subscribeSearch(int count, int page);

    void getMoreData(int count, int page);
}
