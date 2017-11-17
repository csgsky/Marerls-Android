package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gy.allen.marerls.R;

/**
 * Created by allen on 17/10/25.
 */

public class AboutFragment extends Fragment {

    private View mView;
    private Toolbar mToolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_about, null);
        initView();
        return mView;
    }

    private void initView() {
        mToolbar = mView.findViewById(R.id.toolbar);
        mToolbar.setTitle("关于");
    }

}
