package com.route.news_application.api

import com.route.news_application.api.models.EverythingResponse
import com.route.news_application.api.models.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/v2/top-headlines")
    fun getSources(@Query("apiKey") apiKey : String) : Call<SourcesResponse>

    @GET("/v2/everything")
    fun getEverything(@Query("apiKey") apiKey : String) : Call<EverythingResponse>
}