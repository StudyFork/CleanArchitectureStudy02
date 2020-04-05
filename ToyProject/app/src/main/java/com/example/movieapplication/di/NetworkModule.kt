package com.example.movieapplication.di

import com.example.movieapplication.BuildConfig
import com.example.movieapplication.constant.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(Constant.baseUrl)
            .client(get())
            .addConverterFactory(get())
            .build()
    }

    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.createAsync()
    }

    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().apply {

            //TimeOut 시간을 지정합니다.
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)

            // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
            addInterceptor(get(named("interceptor_debug")))

            // Header 또는 QueryParam을 추가해 줍니다.
            addInterceptor(get(named("interceptor_header")))
        }.build()
    }

    single(named("interceptor_debug")) {

        //val debug: Boolean by inject(named("debug"))

        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single(named("interceptor_header")) {
        Interceptor { chain ->

            val request = chain.request()
            val httpUrl = request.url().newBuilder()
                .addQueryParameter("api_key", Constant.apiKey)
                .build()

            chain.proceed(
                request.newBuilder()
                    //.addHeader()
                    .url(httpUrl)
                    .build()
            )
        }
    }
}