package com.example.toyproject.network

import okhttp3.logging.HttpLoggingInterceptor

fun createLogInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)