package com.example.data.remote.errorHandler

interface ErrorHandler {
    operator fun invoke(throwable: Throwable) : ErrorEntity
}