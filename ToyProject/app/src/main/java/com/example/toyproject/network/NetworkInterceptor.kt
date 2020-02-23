package com.example.toyproject.network

import okhttp3.logging.HttpLoggingInterceptor

fun initLogInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
}
