package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gy.allen.marerls.R;

public class NetSongListFragment extends Fragment {
    private static final String TYPE = "type";

    public static NetSongListFragment newInstance(int type) {
        NetSongListFragment netSongListFragment = new NetSongListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        netSongListFragment.setArguments(bundle);
        return netSongListFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_net_music, container, false);
        return rootView;
    }
}
