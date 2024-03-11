package com.route.news_application.data.repos.data_sources.remote_datasource

import com.route.news_application.models.Articles
import com.route.news_application.models.SourcesResponse

interface RemoteDataSource {

    fun loadSources(category:String) : SourcesResponse

    fun loadArticles(sourceId : String) : Articles

    fun searchViewWithCall(sourceId : String, query:String?) : Articles
}