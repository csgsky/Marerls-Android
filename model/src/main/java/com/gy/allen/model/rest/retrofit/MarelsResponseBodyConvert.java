package com.gy.allen.model.rest.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.gy.allen.model.response.StatusEntity;
import com.gy.allen.model.rest.exception.ErrorMsgException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

public class MarelsResponseBodyConvert<T> implements Converter<ResponseBody, T> {

    private final Gson mGson;
    private final TypeAdapter<T> mTypeAdapter;

    MarelsResponseBodyConvert(Gson gson, TypeAdapter adapter) {
        mGson = gson;
        mTypeAdapter = adapter;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.toString();
        try {
            StatusEntity status = mGson.fromJson(response, StatusEntity.class);
            if (status.isInvalidCode()) {
                value.close();
                throw new ErrorMsgException(status.error_code, status.error_msg);
            }

            MediaType contentType = value.contentType();
            Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
            ByteArrayInputStream inputStream = new ByteArrayInputStream(response.getBytes());
            if (charset == null) throw new NullPointerException("charset == null");
            InputStreamReader reader = new InputStreamReader(inputStream, charset);
            JsonReader jsonReader = mGson.newJsonReader(reader);
            return mTypeAdapter.read(jsonReader);

        } finally {
            value.close();
        }
    }
}
