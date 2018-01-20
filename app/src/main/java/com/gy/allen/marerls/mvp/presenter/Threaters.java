package com.gy.allen.marerls.mvp.presenter;


import com.gy.allen.domain.ThreatersUseCase;
import com.gy.allen.marerls.mvp.view.ThreaterView;
import com.gy.allen.marerls.mvp.view.View;
import com.gy.allen.model.response.ThreatersResp;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by allen on 18/1/20.
 */

public class Threaters implements Presenter {

    private final ThreatersUseCase usecase;
    private Subscription subscription;
    private ThreaterView view;
    private int page = 0;

    @Inject
    public Threaters(ThreatersUseCase usecase) {
        this.usecase = usecase;
    }

    @Override
    public void attachView(View v) {
        view = (ThreaterView) v;
    }

    public void getThreaters() {
        Disposable subscribe = usecase.getThreatorList(1 + "").subscribe(this::threatersResponse, this::showError);
    }

    private void showError(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void threatersResponse(ThreatersResp resp) {
        page = 1;
        view.showThreaters(resp);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }



    @Override
    public void onCreate() {

    }
}
