package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.adapter.HomeAdapter;
import com.gy.allen.model.response.ThreatersResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allen on 17/10/25.
 */

public class GankFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View mView;
    private Toolbar mToolbar;
    private MainActivity mActivity;
    private RecyclerView mRecyclerView;
    private List<ThreatersResp.SubjectsBean> subject = new ArrayList<>();
    private HomeAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_gank, null);
        mActivity = (MainActivity) getActivity();
        initView();
        initData();
        return mView;
    }

    private void initData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mActivity.getThreaterPresenter()
                .getThreaters("1")
                .subscribe(this::threatersLists, this::showError);
    }

    private void initView() {
        mToolbar = mView.findViewById(R.id.toolbar);
        mRecyclerView = mView.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = mView.findViewById(R.id.swipeRefreshLayout);
        mToolbar.setTitle("影院电影");
        mSwipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(manager);
        adapter = new HomeAdapter(mActivity, subject);
        mRecyclerView.setAdapter(adapter);
    }

    private void showError(Throwable throwable) {
        mSwipeRefreshLayout.setRefreshing(false);
        throwable.printStackTrace();
    }

    private void threatersLists(ThreatersResp resp) {
        mSwipeRefreshLayout.setRefreshing(false);
        subject.clear();
        subject.addAll(resp.getSubjects());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mActivity.getThreaterPresenter()
                .getThreaters("1")
                .subscribe(this::threatersLists, this::showError);
    }
}
