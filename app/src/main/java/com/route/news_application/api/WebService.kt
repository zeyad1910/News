package com.route.news_application.api

import com.route.news_application.data.models.EverythingResponse
import com.route.news_application.data.models.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/v2/top-headlines/sources?")
    suspend fun getSources(@Query("category") category: String,
                   @Query("apiKey") apiKey : String
    ) : SourcesResponse

    @GET("/v2/everything")
    suspend fun getEverything(@Query("sources") article : String,
                      @Query("apiKey") apiKey : String) : EverythingResponse
    @GET("/v2/everything")
    suspend fun getEverythingForSearch(@Query("q") searchText:String?,
                               @Query("sources") article : String,
                               @Query("apiKey") apiKey : String) : EverythingResponse
}