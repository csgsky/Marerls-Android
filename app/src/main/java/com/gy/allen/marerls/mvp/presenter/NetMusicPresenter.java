package com.gy.allen.marerls.mvp.presenter;

import com.gy.allen.marerls.contract.NetMusicContract;
import com.gy.allen.marerls.mvp.BaseRxPresenter;

import javax.inject.Inject;

public class NetMusicPresenter extends BaseRxPresenter<NetMusicContract.View> implements NetMusicContract.Presenter {

    private int mType = 1;

    @Inject
    NetMusicPresenter(){}

    public void setType(int type) {
        this.mType = type;
    }

    @Override
    public void loadCacheMusicList() {

    }

    @Override
    public void loadNetMusicList() {

    }

    @Override
    public void loadMoreNetMusicList() {

    }
}
