package com.gy.allen.model.repository;


import com.gy.allen.model.response.NetSongEntity;

import io.reactivex.Observable;

public interface NetSongRepository {
    /**
     * 获取网络数据
     * @param method
     * @param songId
     * @return
     */
    Observable<NetSongEntity> getNetSongData(String method, long songId);
}
