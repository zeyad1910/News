package com.route.news_application.api

import android.content.Context
import android.util.Log
import com.route.news_application.ApplicationContextForCache
import com.route.news_application.interceptors.Interceptors
import com.route.news_application.ui.HomeActivity
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    const val apiKey = "5726c7cc55fb426eb40713b9389af78b"
    private const val BASE_URL="https://newsapi.org/"
    private var retrofit: Retrofit?=null

//        fun cache(context: Context): Cache {
//        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
//        val cache = Cache(context.cacheDir, cacheSize)
//        return cache
//        }
    fun service() : WebService? {
        if(retrofit ==null){
            Interceptors.loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClint = OkHttpClient.Builder()
//                .addInterceptor(Interceptors.loggingInterceptor)
//                .addInterceptor(Interceptors.offLineInterceptor)
//                .addNetworkInterceptor(Interceptors.onLineInterceptor)
//                .cache(cache(ApplicationContextForCache().applicationContext))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClint)
                .build()
        }
       return retrofit?.create(WebService::class.java)
    }

}