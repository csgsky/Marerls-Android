package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;

import com.gy.allen.marerls.R;
import com.gy.allen.marerls.base.BaseFragment;
import com.gy.allen.marerls.contract.NetMusicContract;
import com.gy.allen.marerls.mvp.presenter.NetMusicPresenter;
import com.gy.allen.model.NetSongBean;

import java.util.List;

public class NetSongListFragment extends BaseFragment<NetMusicPresenter> implements NetMusicContract.View {
    private static final String TYPE = "type";

    public static NetSongListFragment newInstance(int type) {
        NetSongListFragment netSongListFragment = new NetSongListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        netSongListFragment.setArguments(bundle);
        return netSongListFragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_net_music;
    }

    @Override
    public void loadCacheData(List<NetSongBean> songListBeen) {

    }

    @Override
    public void loadSuccess(List<NetSongBean> songListBeen) {

    }

    @Override
    public void setEmptyView() {

    }

    @Override
    public void loadFail(Throwable throwable) {

    }

    @Override
    public void loadMoreSuccess(List<NetSongBean> songListBeen) {

    }

    @Override
    public void loadMoreEnd() {

    }

    @Override
    public void loadMoreFail(Throwable throwable) {

    }
}
