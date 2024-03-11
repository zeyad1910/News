package com.route.news_application.data.repos.data_sources.local_datasource

import com.route.news_application.models.Articles
import com.route.news_application.models.Source

interface LocalDataSource {

   suspend fun loadSources(category:String) : List<Source?>

    suspend fun loadArticles(sourceId:String) : List<Articles?>

    suspend fun saveSources(list : List<Source?>)

}