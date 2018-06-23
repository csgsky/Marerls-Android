package com.gy.allen.model.repository.Impl;


import com.gy.allen.model.repository.NetSongRepository;
import com.gy.allen.model.response.NetSongEntity;
import com.gy.allen.model.response.OnlineSongEntity;
import com.gy.allen.model.rest.api.SongService;
import com.gy.allen.model.rest.retrofit.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class NetSongRepositoryImpl implements NetSongRepository {
    @Override
    public Observable<NetSongEntity> getNetSongData(String method, long songId) {
        return RetrofitHelper.newInstance().createApi(SongService.class)
                .getOnlineSongData(method,songId)
                .map(new Function<OnlineSongEntity, NetSongEntity>() {
                    @Override
                    public NetSongEntity apply(OnlineSongEntity onlineSongEntity) throws Exception {
                        return null;
                    }
                });
    }
}
