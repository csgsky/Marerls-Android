package com.gy.allen.model.rest;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by allen on 18/1/19.
 */

public class ApiSp {
    private Context context;
    private SharedPreferences sp;

    public Context getContext() {
        return context;
    }

    private SharedPreferences getSp() {
        if (sp != null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp;
    }

    @Inject
    public ApiSp(Context context) {
        this.context = context;
    }
}
