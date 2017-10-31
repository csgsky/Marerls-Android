package com.gy.allen.marerls.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gy.allen.marerls.R;
import com.gy.allen.marerls.adapter.MeiZiAdapter;
import com.gy.allen.marerls.data.GankMeiZiBean;
import com.gy.allen.marerls.mvp.presenter.impl.MeiZiPresenterImpl;
import com.gy.allen.marerls.mvp.view.MeiZiView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by allen on 17/10/25.
 */

// 展示妹子图片的 fragment
public class MeiZiFragment extends Fragment implements MeiZiView {
    private View mView;
    private RecyclerView mRecyclerView;
    private MeiZiPresenterImpl mPresenter;
    private List<GankMeiZiBean.ResultsBean> meizi = new ArrayList<>();
    private MeiZiAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_meizi, null);
        // initData(); // 模拟数据
        initPresenter();
        initView();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        mPresenter.subscribeMeiZi(1);
    }

    private void initPresenter() {
        mPresenter = new MeiZiPresenterImpl(this, new CompositeDisposable());
    }

    private void initView() {
        mRecyclerView = mView.findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MeiZiAdapter(getActivity(), meizi);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void setMeiZiInfo(GankMeiZiBean meiZiInfo) {
        if (!meiZiInfo.isError()) {
            List<GankMeiZiBean.ResultsBean> results = meiZiInfo.getResults();
            meizi.addAll(results);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadError() {
        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_LONG).show();
    }
}
