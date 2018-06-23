package com.gy.allen.marerls.contract;

import com.gy.allen.marerls.base.IBaseView;
import com.gy.allen.model.NetSongBean;

import java.util.List;

public interface NetMusicContract {
    interface Presenter {
        void loadCacheMusicList();
        void loadNetMusicList();
        void loadMoreNetMusicList();
    }

    interface View extends IBaseView {
        void loadCacheData(List<NetSongBean> songListBeen);

        void loadSuccess(List<NetSongBean> songListBeen);

        void setEmptyView();

        void loadFail(Throwable throwable);

        void loadMoreSuccess(List<NetSongBean> songListBeen);

        void loadMoreEnd();

        void loadMoreFail(Throwable throwable);
    }
}
