package com.example.cafeyntest.network

sealed class RequestResult<out R> {
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val throwable: Throwable) : RequestResult<Nothing>()
}