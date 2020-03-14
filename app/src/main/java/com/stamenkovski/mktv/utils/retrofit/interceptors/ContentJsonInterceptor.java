package com.stamenkovski.mktv.utils.retrofit.interceptors;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ContentJsonInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Content-Type","application/json");
        builder.addHeader("Accept","application/json");
        return chain.proceed(builder.build());
    }
}
