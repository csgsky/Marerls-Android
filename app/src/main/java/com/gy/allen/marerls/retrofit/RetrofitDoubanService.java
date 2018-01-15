package com.gy.allen.marerls.retrofit;

import com.gy.allen.marerls.data.ThreatersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by allen on 18/1/15.
 */

public interface RetrofitDoubanService {

    @GET(DoubanApi.API_BASE_DOUBAN + DoubanApi.API_DOUBAN_IN_THREATERS)
    Observable<ThreatersResponse> getThreaters(@Query("start") String start);
}
