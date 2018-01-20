package com.gy.allen.marerls.mvp.presenter;


import com.gy.allen.marerls.mvp.view.View;

/**
 * Created by allen on 18/1/20.
 */

public interface Presenter {

    void onStart();

    void onStop();

    void onPause();

    void attachView(View v);

    void onCreate();
}
