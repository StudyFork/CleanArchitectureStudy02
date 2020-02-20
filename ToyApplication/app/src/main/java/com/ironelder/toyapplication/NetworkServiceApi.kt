package com.ironelder.toyapplication

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkServiceApi {
    val movieServiceApi: NetworkService by lazy {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return@lazy retrofit.create(NetworkService::class.java)
    }

    val imageServiceApi: NetworkService by lazy {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(IMAGE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return@lazy retrofit.create(NetworkService::class.java)
    }

    private val okHttpClient : OkHttpClient by lazy {
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(getNetworkInterceptor())
        }
        return@lazy httpClient.build()
    }

    private fun getNetworkInterceptor():Interceptor {
        return  Interceptor { chain ->
            val  original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("api_key", API_KEY)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}