package com.example.data.remote.errorHandler

interface ErrorHandler {
    fun getError(throwable: Throwable) : ErrorEntity
}