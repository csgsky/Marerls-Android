package com.gy.allen.model.rest.retrofit;

import com.gy.allen.model.BuildConfig;
import com.gy.allen.model.rest.constants.NetConstants;
import com.gy.allen.model.rest.interceptor.BaseUrlInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static RetrofitHelper INSTANCE;


    private Retrofit mRetrofit;

    public static RetrofitHelper newInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitHelper.class) {
                INSTANCE = new RetrofitHelper();
            }
        }
        return INSTANCE;
    }

    {
        mRetrofit = new Retrofit.Builder()
                .client(initOkHttp())
                .baseUrl(NetConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T createApi(Class<T> paramsClass) {
        return mRetrofit.create(paramsClass);
    }

    private OkHttpClient initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.addInterceptor(new BaseUrlInterceptor());
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

}
