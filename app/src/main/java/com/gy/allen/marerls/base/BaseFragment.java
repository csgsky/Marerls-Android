package com.gy.allen.marerls.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gy.allen.marerls.App;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.injector.components.DaggerFragmentComponent;
import com.gy.allen.marerls.injector.components.FragmentComponent;
import com.gy.allen.marerls.injector.modules.FragmentModule;
import com.gy.allen.marerls.util.ScreenUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IBaseView {

    @Inject
    protected T mPresenter;
    protected Activity mActivity;
    private Unbinder mUnBinder;
    private Toolbar mToolbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutRes(), container, false);
        mUnBinder = ButterKnife.bind(this, rootView);
        initInjector();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = view.findViewById(R.id.toolbar_common);
        setToolbar();
        initViews();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
        loadData();
    }

    protected void setToolbar() {
        if (mToolbar != null) {
            ((AppCompatActivity)mActivity).setSupportActionBar(mToolbar);
        }
    }

    protected void setToolbarPadding() {
        if (mToolbar != null) {
            mToolbar.getLayoutParams().height += ScreenUtils.Companion.getStatusBarHeight(mActivity.getApplicationContext());
            mToolbar.setPadding(0, ScreenUtils.Companion.getStatusBarHeight(mActivity.getApplicationContext()), 0 , 0);
        }
    }

    protected void setDrawerSync() {
        if (mToolbar != null) {
           DrawerLayout mDrawerLayout = mActivity.findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(mActivity,
                    mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerToggle.syncState();
            mDrawerLayout.addDrawerListener(drawerToggle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        if (null != mUnBinder) {
            mUnBinder.unbind();
        }
    }

    protected abstract int getLayoutRes();

    protected void initInjector() {

    }

    protected void initViews() {

    }

    protected void loadData() {

    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }
}
