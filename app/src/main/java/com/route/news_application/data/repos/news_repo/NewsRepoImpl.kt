package com.route.news_application.data.repos.news_repo

import com.route.news_application.data.repos.data_sources.ConnectivityChecker
import com.route.news_application.data.repos.data_sources.local_datasource.LocalDataSource
import com.route.news_application.data.repos.data_sources.remote_datasource.RemoteDataSource
import com.route.news_application.models.Articles
import com.route.news_application.models.Source

class NewsRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource)  : NewsRepo {
    override suspend fun loadSources(category: String,apiKey:String) : List<Source?>? {
        if(ConnectivityChecker.isNetworkAvailable()) {
            val response = remoteDataSource.loadSources(category, apiKey)
            return response?.sources!!
        }else{
          return localDataSource.loadSources(category)
        }
    }
    override suspend fun loadArticles(sourceId: String,apiKey:String) : List<Articles?>? {
        if(ConnectivityChecker.isNetworkAvailable()){
            return remoteDataSource.loadArticles(sourceId,apiKey)?.articles
        }else{
            return localDataSource.loadArticles(sourceId)
        }
    }
    override suspend fun searchViewWithCall(sourceId: String, query: String?,apiKey:String)
    : List<Articles?>? {
        return remoteDataSource.searchViewWithCall(query!!,sourceId,apiKey)?.articles
    }
}