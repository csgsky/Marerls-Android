package com.gy.allen.marerls.interfaces;

import android.view.View;

import com.gy.allen.marerls.data.GankMeiZiBean;

/**
 * Created by allen on 17/10/31.
 */

public interface MeiZiListClickListener {
    void onMeiZiClick(View v, View card, GankMeiZiBean.ResultsBean meizi);
}
