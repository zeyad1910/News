package com.route.news_application.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private const val BASE_URL="https://newsapi.org/"
    private var retrofit: Retrofit?=null

    fun getInstance() : WebService? {
        if(retrofit ==null){
            val loggingInterceptor = HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
                override fun log(message: String) {
                    Log.d("ApiManager","Body:$message")
                }
            })
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClint = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
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