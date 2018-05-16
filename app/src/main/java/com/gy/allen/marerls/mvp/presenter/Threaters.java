package com.gy.allen.marerls.mvp.presenter;


import com.gy.allen.domain.ThreatersUseCase;
import com.gy.allen.marerls.mvp.view.ThreaterView;
import com.gy.allen.marerls.mvp.view.View;
import com.gy.allen.model.response.ThreatersResp;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by allen on 18/1/20.
 */

public class Threaters implements Presenter {

    private final ThreatersUseCase usecase;
    private ThreaterView view;

    @Inject
    public Threaters(ThreatersUseCase usecase) {
        this.usecase = usecase;
    }

    @Override
    public void attachView(View v) {
        view = (ThreaterView) v;
    }

    public Observable<ThreatersResp> getThreaters(String page) {
        return usecase.getThreaterList(page);
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
