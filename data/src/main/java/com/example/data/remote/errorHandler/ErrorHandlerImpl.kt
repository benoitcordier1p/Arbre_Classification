package com.example.data.remote.errorHandler

import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun retroError(code : Int) : ErrorEntity{
        return when (code) {
            400 -> ErrorEntity.BadRequest
            401 or 403 -> ErrorEntity.Unauthorized
            404 -> ErrorEntity.NotFound
            406 or 408 -> ErrorEntity.Network
            else -> ErrorEntity.Unknown
        }
    }
}