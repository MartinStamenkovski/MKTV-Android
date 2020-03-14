package com.stamenkovski.mktv.utils.retrofit.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by martin on 3/18/18.
 */
@Deprecated
public class RetryRequestInterceptor implements Interceptor {

    private static final int UNAUTHORIZED = 401;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        int tryCount = 0;

        while (!response.isSuccessful() && tryCount < 3 && response.code() != UNAUTHORIZED) {
            tryCount++;
            response = chain.proceed(request);
        }
        return response;
    }
}
