package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gy.allen.marerls.R;
import com.gy.allen.marerls.base.BaseFragment;

public class NetSongFragment extends BaseFragment {
    public static NetSongFragment newInstance() {
        NetSongFragment fragment = new NetSongFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_net_song;
    }
}
