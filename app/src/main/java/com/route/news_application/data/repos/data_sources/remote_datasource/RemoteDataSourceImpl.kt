package com.route.news_application.data.repos.data_sources.remote_datasource

import com.route.news_application.models.Articles
import com.route.news_application.models.SourcesResponse

class RemoteDataSourceImpl : RemoteDataSource {
    override fun loadSources(category: String): SourcesResponse {
        TODO("Not yet implemented")
    }

    override fun loadArticles(sourceId: String): Articles {
        TODO("Not yet implemented")
    }

    override fun searchViewWithCall(sourceId: String, query: String?): Articles {
        TODO("Not yet implemented")
    }
}