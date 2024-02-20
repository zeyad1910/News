package com.route.news_application.interceptors


import okhttp3.Interceptor
import okhttp3.Response

object Interceptors {

    val onLineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .addHeader("Cache-Control","public, maxAge=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    val offLineInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val maxSteel = 60 * 60 * 24 * 30; // Offline cache available for 30 days
            request.newBuilder()
                .addHeader("Cache-Control", "public, only-if-cached, max-stale=$maxSteel")
                .removeHeader("Pragma")
                .build()
            return chain.proceed(request)
        }
    }



}