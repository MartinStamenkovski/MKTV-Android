package com.stamenkovski.mktv.utils.retrofit;

/**
 * Created by martin on 2/7/18.
 */

class RT private constructor() {

    companion object {
        @JvmStatic
        @get:JvmName("request")
        val request: RetrofitInterface = RetrofitBuilder.getRetrofitInterface()
    }
}
