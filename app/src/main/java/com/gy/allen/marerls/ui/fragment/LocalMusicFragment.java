package com.gy.allen.marerls.ui.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;



import com.bilibili.magicasakura.utils.ThemeUtils;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.base.BaseFragment;
import com.gy.allen.marerls.widget.TickToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class LocalMusicFragment extends BaseFragment {

    @BindView(R.id.toolbar_common)
    TickToolbar mToolbarCommon;

    @BindView(R.id.status_bar)
    View mStatusBar;
    @BindView(R.id.tab_local_music)
    TabLayout tabLayout;
    @BindView(R.id.vp_local_music)
    ViewPager mVpLocalMusic;
    @BindArray(R.array.LocalhostMusicTitleArr)
    String[] mMusicTitle;

    public static LocalMusicFragment newInstance() {
        LocalMusicFragment localMusicFragment = new LocalMusicFragment();
        Bundle bundle = new Bundle();
        localMusicFragment.setArguments(bundle);
        return localMusicFragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_local_music;
    }

    @Override
    protected void initViews() {
        setTabBackground();
        setDrawerSync();
        mToolbarCommon.setElevation(0f);
        setViewpager();
    }

    private void setViewpager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(SongFragment.newInstance());
        fragments.add(AlbumFragment.newInstance());
        fragments.add(ArtistFragment.newInstance());
        LocalMusicPagerAdapter adapter = new LocalMusicPagerAdapter(getChildFragmentManager(), fragments, mMusicTitle);
        mVpLocalMusic.setOffscreenPageLimit(3);
        mVpLocalMusic.setAdapter(adapter);
        tabLayout.setupWithViewPager(mVpLocalMusic);
    }

    private void setTabBackground() {
        ColorStateList stateList = ThemeUtils.getThemeColorStateList(mActivity, R.color.color_tab);
        tabLayout.setBackgroundColor(stateList.getDefaultColor());
        mStatusBar.setBackgroundColor(stateList.getDefaultColor());
    }


    class LocalMusicPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private String[] titles;

        public LocalMusicPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
