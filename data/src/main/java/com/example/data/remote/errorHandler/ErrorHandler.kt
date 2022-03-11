package com.example.data.remote.errorHandler

interface ErrorHandler {

    fun retroError(code : Int) : ErrorEntity
}