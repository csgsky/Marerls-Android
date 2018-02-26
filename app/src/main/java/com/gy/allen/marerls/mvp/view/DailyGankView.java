package com.gy.allen.marerls.mvp.view;

import com.gy.allen.model.response.DailyGankResp;

/**
 * Created by allen on 18/2/26.
 */

public interface DailyGankView extends View {
    void setDailyInfo(DailyGankResp resp);
    void onError(String msg);
}
