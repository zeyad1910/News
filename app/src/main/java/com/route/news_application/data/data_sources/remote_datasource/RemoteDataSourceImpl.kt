package com.route.news_application.data.data_sources.remote_datasource

import com.route.news_application.api.ApiManager
import com.route.news_application.data.models.EverythingResponse
import com.route.news_application.data.models.SourcesResponse

class RemoteDataSourceImpl() : RemoteDataSource {
    override suspend fun loadSources(category:String,apiKey:String): SourcesResponse? {
        return ApiManager.service()?.getSources(category,apiKey)
    }

    override suspend fun loadArticles(sourceId : String,apiKey:String): EverythingResponse? {
        return ApiManager.service()?.getEverything(sourceId,apiKey)
    }

    override suspend fun searchViewWithCall(sourceId : String, query:String?,apiKey:String): EverythingResponse? {
        return ApiManager.service()?.getEverythingForSearch(query,sourceId,ApiManager.apiKey)
    }
}