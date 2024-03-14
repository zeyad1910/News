package com.route.news_application.data.data_sources.local_datasource

import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source

interface LocalDataSource {

   suspend fun loadSources(category:String) : List<Source?>

    suspend fun loadArticles(sourceId:String) : List<Articles?>

    suspend fun saveSources(list : List<Source?>)

    suspend fun deleteSourcesList(category: String)
    suspend fun deleteArticlesList(sourceId:String)

    suspend fun saveArticles(list: List<Articles?>)
}