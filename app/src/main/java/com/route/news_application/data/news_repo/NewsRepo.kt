package com.route.news_application.data.news_repo

import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source

interface NewsRepo {

    suspend fun loadSources(category:String,apiKey:String) : List<Source?>

    suspend fun loadArticles(sourceId : String,apiKey: String) : List<Articles?>

   suspend fun searchViewWithCall(sourceId : String, query:String?,apiKey:String) : List<Articles?>?
}