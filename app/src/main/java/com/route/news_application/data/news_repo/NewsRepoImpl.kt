package com.route.news_application.data.news_repo

import com.route.news_application.data.data_sources.ConnectivityChecker
import com.route.news_application.data.data_sources.local_datasource.LocalDataSource
import com.route.news_application.data.data_sources.remote_datasource.RemoteDataSource
import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source

class NewsRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
)  : NewsRepo {
    override suspend fun loadSources(category: String,apiKey:String) : List<Source?> {
        if(ConnectivityChecker.isNetworkAvailable()) {
            val sourcesResponse = remoteDataSource.loadSources(category,apiKey)
            sourcesResponse?.sources?.let {
                localDataSource.deleteSourcesList(category)
                localDataSource.saveSources(it)
            }
            return sourcesResponse?.sources!!
        }else{
            return localDataSource.loadSources(category)
        }
    }
    override suspend fun loadArticles(sourceId: String,apiKey:String) : List<Articles?> {
         if(ConnectivityChecker.isNetworkAvailable()){
            val articlesResponse =
                remoteDataSource.loadArticles(sourceId,apiKey)
             articlesResponse?.articles?.let {
                 localDataSource.deleteArticlesList(sourceId)
                 localDataSource.saveArticles(it)
             }
                 return articlesResponse?.articles!!
        }else{
            return localDataSource.loadArticles(sourceId)
         }
    }
    override suspend fun searchViewWithCall(sourceId: String, query: String?,apiKey:String)
    : List<Articles?>? {
        return remoteDataSource.searchViewWithCall(query!!,sourceId,apiKey)?.articles
    }
}