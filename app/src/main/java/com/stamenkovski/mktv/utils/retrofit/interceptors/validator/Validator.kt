package com.martin.retrofitwrapper.retrofit.interceptors.validator

import okhttp3.Interceptor
import okhttp3.Response

class Validator : Interceptor {

    @Throws(ValidatorException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {

        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code() !in 200 until 400) {
            throw ValidatorException(APIError.errorMessage(response), response)
        }
        return response
    }
}