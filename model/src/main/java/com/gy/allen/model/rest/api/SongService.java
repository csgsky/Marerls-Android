package com.gy.allen.model.rest.api;

import com.gy.allen.model.response.OnlineSongEntity;
import com.gy.allen.model.rest.constants.NetConstants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SongService {
    @Headers("BaseUrlHead:BaiduApi")
    @GET(NetConstants.URL_PARAM)
    Observable<OnlineSongEntity> getOnlineSongData(
            @Query(NetConstants.Param.METHOD) String method,
            @Query(NetConstants.Param.SONG_ID) long songId);


}
