package com.route.news_application.data.repos.news_repo

import com.route.news_application.api.ApiManager
import com.route.news_application.data.repos.data_sources.local_datasource.LocalDataSource
import com.route.news_application.data.repos.data_sources.remote_datasource.RemoteDataSource
import com.route.news_application.models.Articles
import com.route.news_application.models.EverythingResponse
import com.route.news_application.models.Source
import com.route.news_application.models.SourcesResponse

class NewsRepoImpl(val remoteDataSource: RemoteDataSource,
                   val localDataSource: LocalDataSource)  : NewsRepo {
    override suspend fun loadSources(category: String,apiKey:String) : List<Source?>? {
            val response = remoteDataSource.loadSources(category,apiKey)
            return response?.sources!!
    }
    override suspend fun loadArticles(sourceId: String,apiKey:String) : List<Articles?>? {
        return remoteDataSource.loadArticles(sourceId,apiKey)?.articles
    }
    override suspend fun searchViewWithCall(sourceId: String, query: String?,apiKey:String) : List<Articles?>? {
        return remoteDataSource.searchViewWithCall(query!!,sourceId,apiKey)?.articles
    }

}