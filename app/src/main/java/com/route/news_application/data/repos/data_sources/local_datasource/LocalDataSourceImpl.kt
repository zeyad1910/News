package com.route.news_application.data.repos.data_sources.local_datasource

import com.route.news_application.models.Articles
import com.route.news_application.models.Source

class LocalDataSourceImpl : LocalDataSource {
    override suspend fun loadSources(category: String): List<Source?> {
        TODO("Not yet implemented")
    }

    override suspend fun loadArticles(sourceId: String): List<Articles?> {
        TODO("Not yet implemented")
    }

    override suspend fun saveSources(list: List<Source?>) {
        TODO("Not yet implemented")
    }

}