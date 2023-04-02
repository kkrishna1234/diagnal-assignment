package com.android.domain.error

import com.android.domain.response.ErrorType

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorType
}
