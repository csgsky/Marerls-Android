package com.gy.allen.marerls.mvp.view;

import android.support.annotation.Nullable;

import com.gy.allen.marerls.data.GankTypeBean;

/**
 * Created by allen on 17/10/25.
 */

public interface SearchView {
    void setSearchInfo(GankTypeBean gankSearchResult);
    void setLoadMoreErr(boolean err);
    void loadMoreGankData(@Nullable GankTypeBean gankSearchResult);
    void showNetworkError();
}
