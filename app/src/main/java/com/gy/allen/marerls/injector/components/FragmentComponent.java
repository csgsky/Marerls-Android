package com.gy.allen.marerls.injector.components;


import android.app.Activity;

import com.gy.allen.marerls.injector.modules.FragmentModule;
import com.gy.allen.marerls.injector.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
