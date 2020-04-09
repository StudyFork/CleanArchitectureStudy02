package com.god.taeiim.movieapp.di

import com.god.taeiim.movieapp.BuildConfig
import com.god.taeiim.movieapp.data.source.MovieApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }


    factory { (chain: Interceptor.Chain) ->
        var req = chain.request()
        val url = req.url().newBuilder()
            .addQueryParameter("api_key", "d8cef1cded16cd9e5bae792da0d70ed7")
            .addQueryParameter("language", "ko-KR")
            .build()
        req = req.newBuilder().url(url).build()
        chain.proceed(req)
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor { get { parametersOf(it) } }
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    single {
        get<Retrofit>().create(MovieApi::class.java)
    }

}
