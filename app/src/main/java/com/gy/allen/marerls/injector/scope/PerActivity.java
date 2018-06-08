package com.gy.allen.marerls.injector.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by allen on 18/1/19.
 */

@Scope @Retention(RUNTIME)
public @interface PerActivity {
}
