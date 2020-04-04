package yunji.cleanarchitecturestudy02.network

import okhttp3.Interceptor
import okhttp3.Response
import yunji.cleanarchitecturestudy02.API_KEY_NAME
import yunji.cleanarchitecturestudy02.API_KEY_VALUE
import java.io.IOException

/*
 * Created by yunji on 09/03/2020
 */
class ApiKeyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val origin = request()
        val httpUrl = origin.url.newBuilder().addQueryParameter(API_KEY_NAME, API_KEY_VALUE).build()
        val request = origin.newBuilder().url(httpUrl).build()
        return@with proceed(request)
    }
}