package com.gy.allen.marerls.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.adapter.HomeAdapter;
import com.gy.allen.marerls.data.ThreatersResponse;
import com.gy.allen.marerls.mvp.presenter.ThreatersPresenter;
import com.gy.allen.marerls.mvp.presenter.impl.ThreatersPresenterImpl;
import com.gy.allen.marerls.mvp.view.GankView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by allen on 17/10/25.
 */

public class GankFragment extends Fragment implements GankView {
    private View mView;
    private Toolbar mToolbar;
    private ThreatersPresenter mPresenter;
    private MainActivity mActivity;
    private RecyclerView mRecyclerView;
    private List<ThreatersResponse.SubjectsBean> subject = new ArrayList<>();
    private HomeAdapter adapter;


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
        mPresenter = new ThreatersPresenterImpl(this, new CompositeDisposable());
        mPresenter.subscribeThreater(0);
    }

    private void initView() {
//        Toast.makeText(mActivity, UseCase.USECASE + "", Toast.LENGTH_LONG).show();
        mToolbar = mView.findViewById(R.id.toolbar);
        mToolbar.setTitle("文章");
        mRecyclerView = mView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(manager);
        adapter = new HomeAdapter(mActivity, subject);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setThreatersList(ThreatersResponse threatersResponse) {
        subject.clear();
        subject.addAll(threatersResponse.getSubjects());
        adapter.notifyDataSetChanged();
//        Toast.makeText(mActivity, threatersResponse.getCount() + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadError() {
        Toast.makeText(mActivity, "有问题", Toast.LENGTH_LONG).show();
    }
}
