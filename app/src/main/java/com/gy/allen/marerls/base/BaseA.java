package com.gy.allen.marerls.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gy.allen.marerls.App;
import com.gy.allen.marerls.injector.components.AppComponent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by allen on 18/1/19.
 */

public abstract class BaseA extends RxAppCompatActivity {
    private App app;

    public App getMyApp() {
        return app;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();
        setupActivityComponent(app.getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent component);

    public AppComponent getAppComponent() {
        if (app == null) {
            return null;
        } else {
            return app.getAppComponent();
        }
    }

    protected BaseA getThis() {
        return this;
    }

    protected String getClassSimpleName() {
        String simpleClassName = "";
        try {
            simpleClassName = getClass().getSimpleName();
        } catch (Exception e) {
            e.printStackTrace();
            simpleClassName = "";
        }
        return simpleClassName;
    }
}
