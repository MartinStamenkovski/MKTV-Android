package com.martin.retrofitwrapper.retrofit.interceptors.validator

import okhttp3.Response
import java.io.IOException

class ValidatorException(message: String?, val response: Response) : IOException(message)