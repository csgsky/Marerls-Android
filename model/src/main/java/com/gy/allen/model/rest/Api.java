package com.gy.allen.model.rest;

import com.gy.allen.model.response.ThreatersResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by allen on 18/1/19.
 */

public interface Api {

    @GET("v2/movie/in_theaters")
    Observable<ThreatersResp>
    getThreaters(
            @Query("start") String start
    );
}
