package com.gy.allen.marerls.mvp;

/**
 * Created by allen on 17/10/25.
 */

public interface BasePresenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView(BaseView v);

    void onCreate();
}
