package com.route.news_application.data.repos.data_sources.remote_datasource

import com.route.news_application.models.EverythingResponse
import com.route.news_application.models.SourcesResponse

interface RemoteDataSource {

   suspend fun loadSources(category:String,apiKey:String) : SourcesResponse?

   suspend fun loadArticles(sourceId : String,apiKey:String) : EverythingResponse?

   suspend fun searchViewWithCall(sourceId : String, query:String?,apiKey:String) : EverythingResponse?
}