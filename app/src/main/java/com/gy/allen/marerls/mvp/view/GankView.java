package com.gy.allen.marerls.mvp.view;

import com.gy.allen.marerls.data.ThreatersResponse;

/**
 * Created by allen on 18/1/15.
 */

public interface GankView {
    void setThreatersList(ThreatersResponse threatersResponse);
    void showLoadError();
}
