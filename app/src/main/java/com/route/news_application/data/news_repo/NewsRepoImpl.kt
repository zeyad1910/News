package com.route.news_application.data.news_repo

import android.util.Log
import com.route.news_application.data.data_sources.ConnectivityChecker
import com.route.news_application.data.data_sources.local_datasource.LocalDataSource
import com.route.news_application.data.data_sources.remote_datasource.RemoteDataSource
import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source
import com.route.news_application.data.models.SourceInArticles

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
    override suspend fun loadArticles(source: SourceInArticles, apiKey:String) : List<Articles?> {
         if(ConnectivityChecker.isNetworkAvailable()){
             Log.d("tt", "network is av")
            val articlesResponse =
                remoteDataSource.loadArticles(source.id!!,apiKey)
             articlesResponse?.articles?.let {
                 Log.d("tt", "source ex ${it[0]?.source}")
                 localDataSource.deleteArticlesList(source)
                 Log.d("tt", "source ex ${it[0]?.source?.id}")
                 localDataSource.saveArticles(it)
             }
    //         return localDataSource.loadArticles(source)
                 return articlesResponse?.articles!!
        }else{
            Log.d("tt", "no internet")
            return localDataSource.loadArticles(source)
         }
    }
    override suspend fun searchViewWithCall(sourceId: String, query: String?,apiKey:String)
    : List<Articles?>? {
        return remoteDataSource.searchViewWithCall(query!!,sourceId,apiKey)?.articles
    }
}