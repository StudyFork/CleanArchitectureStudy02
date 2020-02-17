package com.god.taeiim.movieapp.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

fun provideAuthApi(): MovieApi = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .client(provideOkHttpClient(provideLoggingInterceptor(), AuthInterceptor()))
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(MovieApi::class.java)

private fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
    authInterceptor: AuthInterceptor?
): OkHttpClient = OkHttpClient.Builder()
    .run {
        if (null != authInterceptor) {
            addInterceptor(authInterceptor)
        }
        addInterceptor(interceptor)
        build()
    }

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

internal class AuthInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : Response = with(chain) {
        val newRequest = request().newBuilder().run {
            url(
                request().url().newBuilder()
                    .addQueryParameter("api_key", "d8cef1cded16cd9e5bae792da0d70ed7")
                    .addQueryParameter("language", "ko-KR").build()
            )
            build()
        }
        proceed(newRequest)
    }
}