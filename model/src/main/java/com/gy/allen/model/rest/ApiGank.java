package com.gy.allen.model.rest;

import com.gy.allen.model.response.DailyGankResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by allen on 18/2/26.
 */
//http://gank.io/api/data/Android/10/1 : 分类干货， 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
//http://gank.io/api/history/content/day/2016/05/11 ：获取特定日期干货
public interface ApiGank {

    @GET("history/content/day/{year}/{month}/{day}")
    Observable<DailyGankResp>
    dailyGank(
            @Path("year") String year,
            @Path("month") String month,
            @Path("day") String day
    );
}
