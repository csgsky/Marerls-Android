package com.gy.allen.marerls.retrofit;

import com.gy.allen.marerls.data.GankMeiZiBean;
import com.gy.allen.marerls.data.GankTypeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by allen on 17/10/25.
 */

public interface RetrofitService {

    @GET(Api.API_SEARCH + "query/listview/category/all/count/{count}/page/{page}")
    Observable<GankTypeBean> getSearchResult(@Path("count") int count, @Path("page") int page);

    @GET(Api.API_MEIZI + "{page}")
    Observable<GankMeiZiBean> getGankMeiZiList(@Path("page") String page);

}
