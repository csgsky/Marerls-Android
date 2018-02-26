package com.gy.allen.marerls.mvp.presenter;

import com.gy.allen.domain.GankUseCase;
import com.gy.allen.marerls.mvp.view.DailyGankView;
import com.gy.allen.marerls.mvp.view.View;
import com.gy.allen.model.response.DailyGankResp;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by allen on 18/2/26.
 */

public class DailyGankPresenter implements Presenter {
    private final GankUseCase usecase;
    private DailyGankView view;
    private Disposable subscription;

    @Inject
    public DailyGankPresenter(GankUseCase usecase) {
        this.usecase = usecase;
    }

    public void getDailyGank(String year, String month, String day) {
        subscription = usecase.dailyGank(year, month, day).subscribe(this::dailyGank, this::error);
    }

    private void error(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void dailyGank(DailyGankResp resp) {

        if (resp.isError()) {
            view.onError("有问题。。。");
        } else {
            view.setDailyInfo(resp);
        }
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
    public void attachView(View v) {
        view = (DailyGankView) v;
    }

    @Override
    public void onCreate() {

    }
}
