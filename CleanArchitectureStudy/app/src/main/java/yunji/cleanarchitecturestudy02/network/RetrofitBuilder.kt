package yunji.cleanarchitecturestudy02.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yunji.cleanarchitecturestudy02.BASE_URL
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val TIME_OUT_SEC = 5L

    val service: MovieApi by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(ApiKeyInterceptor())
            .connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
            .build()

        return@lazy Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }
}