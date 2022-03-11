package com.example.data.remote.errorHandler

sealed class ErrorEntity{
    object Network : ErrorEntity()
    object BadRequest : ErrorEntity()
    object NotFound : ErrorEntity()
    object Unauthorized : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object Unknown : ErrorEntity()
    object Blank : ErrorEntity()
}
