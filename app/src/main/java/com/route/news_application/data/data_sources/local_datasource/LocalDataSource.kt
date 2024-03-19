package com.route.news_application.data.data_sources.local_datasource

import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source
import com.route.news_application.data.models.SourceInArticles

interface LocalDataSource {

   suspend fun loadSources(category:String) : List<Source?>

    suspend fun loadArticles(source: SourceInArticles) : List<Articles?>

    suspend fun saveSources(list : List<Source?>)

    suspend fun deleteSourcesList(category: String)

    suspend fun deleteArticlesList(source: SourceInArticles)

    suspend fun saveArticles(list: List<Articles?>)
}