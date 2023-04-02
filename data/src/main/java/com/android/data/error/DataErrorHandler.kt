package com.android.data.error

import com.android.domain.error.ErrorHandler
import com.android.domain.response.ErrorType
import java.io.IOException

class DataErrorHandler() : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorType {
        return when (throwable.cause) {
            is IOException -> ErrorType.FileError
            else -> ErrorType.GenericError(throwable)
        }
    }
}
