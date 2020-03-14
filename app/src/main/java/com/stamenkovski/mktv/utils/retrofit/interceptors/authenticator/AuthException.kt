package com.martin.retrofitwrapper.retrofit.interceptors.authenticator

import java.io.IOException

class AuthException(private val errorMessage: String? = null) : IOException() {
    override val message: String?
        get() = errorMessage
}