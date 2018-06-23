package com.gy.allen.marerls.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.gy.allen.marerls.R;
import com.gy.allen.marerls.base.BaseFragment;
import com.gy.allen.marerls.contract.NetMusicCategoryContract;
import com.gy.allen.marerls.mvp.presenter.NetMusicCategoryPresenter;
import com.gy.allen.marerls.util.DensityUtils;
import com.gy.allen.marerls.util.ScreenUtils;
import com.gy.allen.marerls.widget.TickToolbar;
import com.gy.allen.model.CategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NetSongFragment extends BaseFragment<NetMusicCategoryPresenter> implements NetMusicCategoryContract.View {
    @BindView(R.id.toolbar_common)
    TickToolbar mToolbarCommon;
    @BindView(R.id.vp_net_song)
    ViewPager mVpNetSong;
    @BindView(R.id.tab_net_music)
    TabLayout mTbNetMusic;
    @BindView(R.id.img_song)
    ImageView mImgSong;
    @BindView(R.id.ctl_title)
    CollapsingToolbarLayout mCtlTitle;

    private List<CategoryBean> mCategoryData = new ArrayList<>();
    private static final int TAB_HEIGHT = 45; //45dp

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
        getFragmentComponent().inject(this);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void loadData() {
        mPresenter.loadData();
    }

    @Override
    public Context context() {
        return mActivity;
    }

    @Override
    public void loadCategoryData(List<CategoryBean> categoryBeens) {
        mCategoryData.addAll(categoryBeens);
        NetSongPagerAdapter netSongPagerAdapter = new NetSongPagerAdapter(getChildFragmentManager(), mCategoryData);
        mVpNetSong.setAdapter(netSongPagerAdapter);
        mTbNetMusic.setupWithViewPager(mVpNetSong);
        mVpNetSong.setOffscreenPageLimit(3);
        mPresenter.setCategoryColor(categoryBeens.get(0).imgUrl);
        mCtlTitle.setTitle(categoryBeens.get(0).title);
        mTbNetMusic.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPresenter.setCategoryColor(categoryBeens.get(tab.getPosition()).imgUrl);
                mCtlTitle.setTitle(categoryBeens.get(tab.getPosition()).title);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setCategoryBitmap(Bitmap bitmap) {
        mImgSong.setImageBitmap(bitmap);
    }

    @Override
    public void setCategoryColor(int color) {
        mCtlTitle.setContentScrimColor(color);
    }

    class NetSongPagerAdapter extends FragmentPagerAdapter {

        private List<CategoryBean> mCategoryBeans;

        public NetSongPagerAdapter(FragmentManager fm, List<CategoryBean> categoryBeans) {
            super(fm);
            this.mCategoryBeans = categoryBeans;
        }

        @Override
        public Fragment getItem(int position) {
            CategoryBean categoryBean = mCategoryBeans.get(position);
            return NetSongListFragment.newInstance(categoryBean.type);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mCategoryBeans.get(position).title;
        }

        @Override
        public int getCount() {
            return mCategoryBeans != null ? mCategoryBeans.size() : 0;
        }
    }
}
