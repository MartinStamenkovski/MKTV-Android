package com.stamenkovski.mktv.utils.retrofit.interceptors.network;

import android.content.Context;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This Interceptor is discouraged for usage,
 * if you want to handle network errors it's best if you do it in the activity/fragment where the API call happens.
 */
public class NetworkInterceptor implements Interceptor {

    private Context context;

    public NetworkInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isOnline(context)) {
            throw new NoConnectionException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

}
