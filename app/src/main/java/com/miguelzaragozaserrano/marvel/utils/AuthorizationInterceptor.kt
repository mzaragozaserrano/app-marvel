package com.miguelzaragozaserrano.marvel.utils

import com.miguelzaragozaserrano.characters.utils.extensions.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class AuthorizationInterceptor(
    private val publicKey: String,
    private val privateKey: String,
    private val calendar: Calendar
) : Interceptor {

    @Suppress("MagicNumber")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url

        val ts = (calendar.timeInMillis / 1_000L).toString()
        val hash = "$ts$privateKey$publicKey".md5()
        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_TS, ts)
            .addQueryParameter(QUERY_PARAMETER_API_KEY, publicKey)
            .addQueryParameter(QUERY_PARAMETER_HASH, hash)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    companion object {
        private const val QUERY_PARAMETER_TS = "ts"
        private const val QUERY_PARAMETER_API_KEY = "apikey"
        private const val QUERY_PARAMETER_HASH = "hash"
    }
}