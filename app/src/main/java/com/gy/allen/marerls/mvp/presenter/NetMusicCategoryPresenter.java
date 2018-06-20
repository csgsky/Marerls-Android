package com.gy.allen.marerls.mvp.presenter;

import com.gy.allen.marerls.contract.NetMusicCategoryContract;
import com.gy.allen.marerls.mvp.BaseRxPresenter;

import javax.inject.Inject;

public class NetMusicCategoryPresenter extends BaseRxPresenter<NetMusicCategoryContract.View> implements NetMusicCategoryContract.Presenter {

    @Inject
    public NetMusicCategoryPresenter() {}

    @Override
    public void loadData() {

    }

    @Override
    public void setCategoryColor(String imgUrl) {

    }
}
