package com.gy.allen.marerls.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.gy.allen.marerls.App;
import com.gy.allen.marerls.R;
import com.gy.allen.marerls.injector.components.ActivityComponent;
import com.gy.allen.marerls.injector.components.DaggerActivityComponent;
import com.gy.allen.marerls.injector.modules.ActivityModule;
import com.gy.allen.marerls.mvp.BasePresenter;
import com.gy.allen.marerls.mvp.BaseView;
import com.gy.allen.marerls.util.ScreenUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * Created by allen on 18/1/19.
 */

public abstract class BaseA<T extends BasePresenter> extends RxAppCompatActivity implements BaseView {
    @Inject
    protected T mPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
        loadData();
    }

    private void setViews() {
        setStatusBar();
        setContentView(getLayoutRes());
        setToolbar();
        initViews();
    }

    protected void initInject() {

    }


    protected void initViews() {

    }

    protected void loadData() {

    }

    protected abstract @LayoutRes
    int getLayoutRes();

    private void setStatusBar() {
        View decorView =
                getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorLightTransparent));
    }

    protected ActivityComponent getActivityComponet() {
        return DaggerActivityComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }


    protected BaseA getThis() {
        return this;
    }


    protected void setToolbar()  {
        mToolbar = findViewById(R.id.toolbar_common);
        if (null != mToolbar) {
            mToolbar.getLayoutParams().height += ScreenUtils.Companion.getStausBarHeight(getApplicationContext());
            mToolbar.setPadding(0, ScreenUtils.Companion.getStausBarHeight(getApplicationContext()), 0 , 0);
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
        }
    }

    protected void setToolbarTitle(String title) {
        if (null != mToolbar) mToolbar.setTitle(title);
    }

    protected void hidToolbarTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    protected void showBackIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected String getClassSimpleName() {
        String simpleClassName;
        try {
            simpleClassName = getClass().getSimpleName();
        } catch (Exception e) {
            e.printStackTrace();
            simpleClassName = "";
        }
        return simpleClassName;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
