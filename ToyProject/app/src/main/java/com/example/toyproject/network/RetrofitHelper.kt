package com.example.toyproject.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object okHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(initLogInterceptor())
        .build()
}

object buildRetrofit {
    private val URL = "https://api.themoviedb.org/3/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.okHttpClient)
        .build()
}

fun retrofitService() = buildRetrofit.retrofit.create(RetrofitService::class.java)