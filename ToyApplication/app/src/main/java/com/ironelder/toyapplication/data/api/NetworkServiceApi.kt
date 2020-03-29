package com.ironelder.toyapplication.data.api

import com.ironelder.toyapplication.BuildConfig
import com.ironelder.toyapplication.common.utils.IMAGE_BASE_URL
import com.ironelder.toyapplication.common.utils.MOVIE_BASE_URL
import com.ironelder.toyapplication.common.utils.NETWORK_TIMEOUT
import com.ironelder.toyapplication.data.service.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkServiceApi {
    val movieServiceApi: NetworkService by lazy {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build()
        return@lazy retrofit.create(NetworkService::class.java)
    }

    val imageServiceApi: NetworkService by lazy {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(IMAGE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return@lazy retrofit.create(NetworkService::class.java)
    }

    private val okHttpClient: OkHttpClient by lazy {
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            addNetworkInterceptor(getNetworkLogInterceptor())
        }
        return@lazy httpClient.build()
    }

    private fun getNetworkLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }
}