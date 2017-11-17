package com.gy.allen.marerls.ui.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.gy.allen.marerls.MainActivity;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.adapter.MeiZiAdapter;
import com.gy.allen.marerls.data.GankMeiZiBean;
import com.gy.allen.marerls.interfaces.MeiZiListClickListener;
import com.gy.allen.marerls.mvp.presenter.impl.MeiZiPresenterImpl;
import com.gy.allen.marerls.mvp.view.MeiZiView;
import com.gy.allen.marerls.ui.activity.GankActivity;
import com.gy.allen.marerls.util.Consts;

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
    private Toolbar mToolbar;
    private MainActivity mMainActivity;
    private boolean mMeiZiTouched = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_meizi, null);
        mMainActivity = (MainActivity) getActivity();
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
        mToolbar = mView.findViewById(R.id.toolbar);
        mToolbar.setTitle("妹纸");
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MeiZiAdapter(getActivity(), meizi, new MeiZiListClickListener() {
            @Override
            public void onMeiZiClick(View v, View card, GankMeiZiBean.ResultsBean meizi) {
                if (v == card) {

                    Glide.with(mMainActivity).load(meizi.getUrl()).asBitmap().listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    }).into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            startGankActivity(meizi, card);
                        }
                    });

                }

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startGankActivity(GankMeiZiBean.ResultsBean meizi, View transitView) {
        Intent intent = new Intent(mMainActivity, GankActivity.class);
        intent.putExtra(Consts.EXTRA_IMAGE_URL, meizi.getUrl());
        intent.putExtra(Consts.EXTRA_IMAGE_TITLE, meizi.getDesc());
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mMainActivity, transitView, Consts.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(mMainActivity, intent, compat.toBundle());
        } catch (Exception e) {
            e.printStackTrace();
            mMainActivity.startActivity(intent);
        }

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
        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
    }
}
