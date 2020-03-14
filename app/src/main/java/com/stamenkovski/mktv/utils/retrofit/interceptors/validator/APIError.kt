package com.martin.retrofitwrapper.retrofit.interceptors.validator

import okhttp3.Response

object APIError {

    fun errorMessage(response: Response): String {
        return when (response.code()) {
            400 -> "Bad Request"
            401 -> "Unauthorized"
            404 -> "Route not found"
            408 -> "Request timed out"
            422 -> "Email already exists"

            500 -> "Internal Server error"
            502 -> "Bad gateway"
            504 -> "Gateway timeout"

            else -> "Something went wrong"
        }

    }

}
