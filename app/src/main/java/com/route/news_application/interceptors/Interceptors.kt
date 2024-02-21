package com.route.news_application.interceptors


import android.util.Log
import com.route.news_application.ApplicationContextForCache
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

object Interceptors {
    val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
        override fun log(message: String) {
            Log.e("ApiManager","Body:$message")
        }
    })

    val onLineInterceptor = Interceptor { chain ->
        val response = chain.request()
        val maxAge = 60
        response.newBuilder()
            .addHeader("Cache-Control","public, max-age=60")
            .removeHeader("Pragma")
            .build()
        chain.proceed(response)
    }

    val offLineInterceptor = Interceptor { chain ->
        val request = chain.request()
        val maxSteel = 60 * 60 * 24 * 30; // Offline cache available for 30 days
        request.newBuilder()
            .addHeader("Cache-Control", "public, only-if-cached, max-stale=$maxSteel")
            .removeHeader("Pragma")
            .build()
        chain.proceed(request)
    }


}