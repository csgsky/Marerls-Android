package com.gy.allen.model.rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.gy.allen.model.repository.Repository;
import com.gy.allen.model.response.ThreatersResp;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by allen on 18/1/19.
 */

public class RestDataSource implements Repository{
    public static final int  RESTDATASOURCE = 2432134;

    private ApiSp apiSp;
    public static String END_POINT_DOUBAN = "https://api.douban.com/";
    private static Api api;

    @Inject
    public RestDataSource(
            ApiSp apiSp
    ) {
        this.apiSp = apiSp;
        updateRestApi();
    }

    private Context getContext() {
        return apiSp.getContext();
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean response = info != null && info.isConnected();
        return response;
    }

    public Interceptor provideOfflineCacheIntercepter() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (checkIfHasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }

    public Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                CacheControl cacheControl = null;
                if (!checkIfHasNetwork()) {
                    cacheControl = new CacheControl.Builder()
                            .maxAge(2, TimeUnit.MINUTES)
                            .build();
                } else {
                    cacheControl = new CacheControl.Builder()
                            .maxAge(0, TimeUnit.MINUTES)
                            .build();
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build();
            }
        };
    }

    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File("/data/data/com.gy.allen.marerls/","http-cache"), 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    private void updateRestApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(provideOfflineCacheIntercepter())
                .addInterceptor(provideCacheInterceptor())
                .cache(provideCache())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(END_POINT_DOUBAN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(Api.class);
    }

    @Override
    public Observable<ThreatersResp> getThreatersList(String start) {
        return api.getThreaters(start);
    }
}
