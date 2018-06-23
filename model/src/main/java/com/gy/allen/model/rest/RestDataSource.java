package com.gy.allen.model.rest;

/**
 * Created by allen on 18/1/19.
 */

public class RestDataSource {

//    private ApiSp apiSp;
//    public static String END_POINT_DOUBAN = "https://api.douban.com/";
//    public static String END_POINT_GANK = "http://gank.io/api/";
//    private static SongService api;
//    private static ApiGank apiGank;
//
//    @Inject
//    public RestDataSource(
//            ApiSp apiSp
//    ) {
//        this.apiSp = apiSp;
//        updateRestApi();
//    }
//
//    private Context getContext() {
//        return apiSp.getContext();
//    }
//
//    public boolean checkIfHasNetwork() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
//        boolean response = info != null && info.isConnected();
//        return response;
//    }
//
//    public Interceptor provideOfflineCacheIntercepter() {
//        return new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//                Request request = chain.request();
//                if (checkIfHasNetwork()) {
//                    CacheControl cacheControl = new CacheControl.Builder()
//                            .maxStale(7, TimeUnit.DAYS)
//                            .build();
//
//                    request = request.newBuilder()
//                            .cacheControl(cacheControl)
//                            .build();
//                }
//                return chain.proceed(request);
//            }
//        };
//    }
//
//    public Interceptor provideCacheInterceptor() {
//        return new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Response response = chain.proceed(chain.request());
//                CacheControl cacheControl = null;
//                if (!checkIfHasNetwork()) {
//                    cacheControl = new CacheControl.Builder()
//                            .maxAge(2, TimeUnit.MINUTES)
//                            .build();
//                } else {
//                    cacheControl = new CacheControl.Builder()
//                            .maxAge(0, TimeUnit.MINUTES)
//                            .build();
//                }
//                return response.newBuilder()
//                        .header("Cache-Control", cacheControl.toString())
//                        .build();
//            }
//        };
//    }
//
//    private static Cache provideCache() {
//        Cache cache = null;
//        try {
//            cache = new Cache(new File("/data/data/com.gy.allen.marerls/","http-cache"), 10 * 1024 * 1024);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cache;
//    }
//
//    private void updateRestApi() {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(5, TimeUnit.SECONDS)
//                .addInterceptor(loggingInterceptor)
//                .addNetworkInterceptor(new StethoInterceptor())
//                .addInterceptor(provideOfflineCacheIntercepter())
//                .addInterceptor(provideCacheInterceptor())
//                .cache(provideCache())
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(END_POINT_DOUBAN)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build();
//
//        Retrofit retrofitGank = new Retrofit.Builder()
//                .baseUrl(END_POINT_GANK)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build();
//
//        api = retrofit.create(SongService.class);
//        apiGank = retrofitGank.create(ApiGank.class);
//    }
//
//    @Override
//    public Observable<ThreatersResp> getThreatersList(String start) {
//        return api.getThreaters(start);
//    }
//
//    public Observable<DailyGankResp> dailyGank(String year, String month, String day) {
//        return apiGank.dailyGank(year, month, day);
//    }
}
