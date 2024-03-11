package com.route.news_application.data.repos.data_sources.local_datasource

import com.route.news_application.models.Articles
import com.route.news_application.models.SourcesResponse

interface LocalDataSource {

    fun loadSources(category:String) : SourcesResponse

    fun loadArticles(sourceId : String) : Articles

}