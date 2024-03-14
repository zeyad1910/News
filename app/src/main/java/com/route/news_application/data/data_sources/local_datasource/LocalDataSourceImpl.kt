package com.route.news_application.data.data_sources.local_datasource

import com.route.news_application.data.DatabaseManager
import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source

class LocalDataSourceImpl(private val databaseManager: DatabaseManager) : LocalDataSource {

    override suspend fun loadSources(category: String): List<Source?> {
        return databaseManager.sourcesDao().getSources(category)
    }

    override suspend fun loadArticles(sourceId: String): List<Articles?> {
        return databaseManager.articlesDao().getArticles(sourceId)
    }

    override suspend fun saveSources(list: List<Source?>) {
        val nonNullList = list.filter {
            return@filter it != null
        } as List<Source>
        databaseManager.sourcesDao().addSources(nonNullList)
    }

    override suspend fun deleteSourcesList(category: String) {
        databaseManager.sourcesDao().deleteOldList(category)
    }

    override suspend fun deleteArticlesList(sourceId:String) {
        databaseManager.articlesDao().deleteOldArticles(sourceId)
    }

    override suspend fun saveArticles(list: List<Articles?>) {
        val nonNullList = list.filter {
            return@filter it != null
        } as List<Articles>
        databaseManager.articlesDao().addArticles(nonNullList)
    }

}