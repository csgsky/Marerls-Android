package com.gy.allen.marerls.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.gy.allen.marerls.R;
import com.gy.allen.marerls.base.BaseA;
import com.gy.allen.marerls.injector.components.AppComponent;
import com.gy.allen.marerls.injector.components.DaggerGankComponent;
import com.gy.allen.marerls.injector.modules.ActivityModule;
import com.gy.allen.marerls.injector.modules.GankModule;
import com.gy.allen.marerls.mvp.presenter.DailyGankPresenter;
import com.gy.allen.marerls.mvp.view.DailyGankView;
import com.gy.allen.model.response.DailyGankResp;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by allen on 18/2/26.
 */
public class DailyGankA extends BaseA implements DailyGankView {

    @Inject
    DailyGankPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_gank);
        presenter.attachView(this);
        presenter.getDailyGank("2017","05","10");
    }

    @Override
    public void setDailyInfo(DailyGankResp resp) {
        List<DailyGankResp.ResultsBean> results = resp.getResults();
        if (results.size() > 0) {
            Toast.makeText(this, results.get(0).getTitle(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void setupActivityComponent(AppComponent component) {
        DaggerGankComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(component)
                .gankModule(new GankModule())
                .build()
                .inject(this);
    }
}