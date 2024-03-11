package com.route.news_application.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    const val apiKey = "5726c7cc55fb426eb40713b9389af78b"
    private const val BASE_URL="https://newsapi.org/"
    private var retrofit: Retrofit?=null


    fun service() : WebService? {
        if(retrofit ==null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
       return retrofit?.create(WebService::class.java)
    }

}