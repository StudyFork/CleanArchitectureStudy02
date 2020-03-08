package com.template.nanamare.di

import com.template.nanamare.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DI_RETROFIT_NO_AUTH = "DI_RETROFIT_NO_AUTH"

const val DI_RETROFIT_NO_AUTH_CLIENT = "DI_RETROFIT_NO_AUTH_CLIENT"

const val TIME_OUT = 30_000L

val networkModule: Module = module {

    single {
        GsonConverterFactory.create() as Converter.Factory
    }
    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    single(named(DI_RETROFIT_NO_AUTH_CLIENT)) {
        OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder().apply {
                    // nothing
                }.method(original.method(), original.body()).build()
                it.proceed(request)
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
            .build()
    }

    single(named(DI_RETROFIT_NO_AUTH)) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(get(named(DI_RETROFIT_NO_AUTH_CLIENT)))
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

}
