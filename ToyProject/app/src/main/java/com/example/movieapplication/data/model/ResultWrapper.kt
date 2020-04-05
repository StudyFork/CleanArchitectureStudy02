package com.example.movieapplication.data.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class HttpException(val code: Int, val error: String): ResultWrapper<Nothing>()
    data class NetworkError(val error: String = "Network Error"): ResultWrapper<Nothing>()
}