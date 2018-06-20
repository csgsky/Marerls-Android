package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;

import com.gy.allen.marerls.R;
import com.gy.allen.marerls.base.BaseFragment;
import com.gy.allen.marerls.mvp.presenter.NetMusicCategoryPresenter;
import com.gy.allen.marerls.util.DensityUtils;
import com.gy.allen.marerls.util.ScreenUtils;
import com.gy.allen.marerls.widget.TickToolbar;

import butterknife.BindView;

public class NetSongFragment extends BaseFragment<NetMusicCategoryPresenter> {
    @BindView(R.id.toolbar_common)
    TickToolbar mToolbarCommon;
    private static final int TAB_HEIGHT = 45;//45dp

    public NetSongFragment() {}

    public static NetSongFragment newInstance() {
        NetSongFragment fragment = new NetSongFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_net_song;
    }

    @Override
    protected void initInjector() {
//        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViews() {
        setToolbar();
        setTitlePadding();
        setDrawerSync();
        mToolbarCommon.setElevation(0f);
    }

    private void setTitlePadding() {
        mToolbarCommon.getLayoutParams().height = ScreenUtils.Companion.getActionBarHeight(mActivity.getApplicationContext())
                + ScreenUtils.Companion.getStatusBarHeight(mActivity.getApplicationContext())
                + DensityUtils.Companion.dp2px(mActivity.getApplicationContext(), TAB_HEIGHT);
        mToolbarCommon.setPadding(0,
                ScreenUtils.Companion.getStatusBarHeight(mActivity.getApplicationContext()),
                0,
                DensityUtils.Companion.dp2px(mActivity, TAB_HEIGHT));
    }


}
