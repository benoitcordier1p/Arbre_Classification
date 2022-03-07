package com.example.data.remote.errorHandler

sealed class ErrorEntity{
    object Network : ErrorEntity()
    object NotFound : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object Unknown : ErrorEntity()
}
