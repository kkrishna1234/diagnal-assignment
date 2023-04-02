package com.android.domain.response

sealed class Result<T : Any> {

    data class Success<T : Any>(val data: T) : Result<T>()

    data class Error<T : Any>(val error: ErrorType) : Result<T>()
}
