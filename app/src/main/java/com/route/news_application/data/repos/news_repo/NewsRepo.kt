package com.route.news_application.data.repos.news_repo

import com.route.news_application.models.Articles
import com.route.news_application.models.EverythingResponse
import com.route.news_application.models.Source
import com.route.news_application.models.SourcesResponse
import java.util.concurrent.atomic.AtomicReference

interface NewsRepo {

    suspend fun loadSources(category:String,apiKey:String) : List<Source?>?

    suspend fun loadArticles(sourceId : String,apiKey: String) : List<Articles?>?

   suspend fun searchViewWithCall(sourceId : String, query:String?,apiKey:String) : List<Articles?>?
}