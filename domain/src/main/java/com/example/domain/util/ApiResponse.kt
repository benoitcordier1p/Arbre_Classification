package com.example.domain.util

import com.example.data.remote.errorHandler.ErrorEntity

sealed class ApiResponse<T>(val data: T? = null, val error: ErrorEntity? = null) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(error: ErrorEntity, data: T? = null) : ApiResponse<T>(data, error)
    class Loading<T>(data: T? = null) : ApiResponse<T>(data)
}