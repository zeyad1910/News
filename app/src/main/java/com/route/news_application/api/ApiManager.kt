package com.route.news_application.api

import android.util.Log
import com.route.news_application.interceptors.Interceptors
import com.route.news_application.ui.HomeActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private const val BASE_URL="https://newsapi.org/"
    private var retrofit: Retrofit?=null

    fun service() : WebService? {
        if(retrofit ==null){
            val loggingInterceptor = HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
                override fun log(message: String) {
                    Log.e("ApiManager","Body:$message")
                }
            })
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClint = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
                .addInterceptor(Interceptors.offLineInterceptor)
                .addNetworkInterceptor(Interceptors.onLineInterceptor)
                .cache(HomeActivity().cache())
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