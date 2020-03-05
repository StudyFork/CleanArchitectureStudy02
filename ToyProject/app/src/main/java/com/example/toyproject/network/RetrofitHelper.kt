package com.example.toyproject.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseOkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(initLogInterceptor())
        .build()
}

object BuildRetrofit {
    private val URL = "https://api.themoviedb.org/3/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(BaseOkHttpClient.okHttpClient)
        .build()
}

fun retrofitService() = BuildRetrofit.retrofit.create(RetrofitService::class.java)