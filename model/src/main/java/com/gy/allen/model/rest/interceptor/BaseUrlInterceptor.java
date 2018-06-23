package com.gy.allen.model.rest.interceptor;

import com.gy.allen.model.rest.constants.NetConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseUrlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        String urlHead = originRequest.headers().get(NetConstants.Header.BASE_URL_HEAD);
        HttpUrl oldHttpUrl = originRequest.url();
        if (urlHead != null && !urlHead.isEmpty()) {
            HttpUrl newBaseUrl;
            Request.Builder builder;
            if (NetConstants.Header.BAIDU_HEAD_CONTENT.equals(urlHead)) {
                newBaseUrl = HttpUrl.parse(NetConstants.BASE_URL);
                builder = originRequest
                        .newBuilder()
                        .addHeader(NetConstants.Header.USER_AGENT, NetConstants.Header.USER_AGENT_CONTENT);
            } else if (NetConstants.Header.BANDSINTOWN_HEAD_CONTENT.equals(urlHead)) {
                newBaseUrl = HttpUrl.parse(NetConstants.BASE_ARTIST_URL);
                builder = originRequest.newBuilder();
            } else if (NetConstants.Header.GECIMI_HEAD_CONTENT.equals(urlHead)) {
                newBaseUrl = HttpUrl.parse(NetConstants.BASE_LRC_URL);
                builder = originRequest.newBuilder();
            } else {
                newBaseUrl = oldHttpUrl;
                builder = originRequest.newBuilder();
            }
            if (null == newBaseUrl) {
                return null;
            }
            HttpUrl newFullUrl = oldHttpUrl.newBuilder()
                    .scheme(newBaseUrl.scheme())
                    .host(newBaseUrl.host())
                    .port(newBaseUrl.port())
                    .build();
            return chain.proceed(builder.url(newFullUrl).build());
        } else {
            return chain.proceed(originRequest);
        }
    }
}
