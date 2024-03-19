package com.route.news_application.data.data_sources.local_datasource

import android.util.Log
import com.route.news_application.data.DatabaseManager
import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source
import com.route.news_application.data.models.SourceInArticles

class LocalDataSourceImpl(private val databaseManager: DatabaseManager) : LocalDataSource {

    override suspend fun loadSources(category: String): List<Source?> {
        return databaseManager.sourcesDao().getSources(category)
    }

    override suspend fun loadArticles(source: SourceInArticles): List<Articles?> {
        return databaseManager.articlesDao().getArticles(source)
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

    override suspend fun deleteArticlesList(source: SourceInArticles) {
        databaseManager.articlesDao().deleteOldArticles(source)
    }

    override suspend fun saveArticles(list: List<Articles?>) {
        val nonNullList = list.filter {
//            if (it != null) {
//                return@filter it!!.sourceId != null
//            }
            return@filter it != null
        } as List<Articles>
        Log.d("tt", "not null source ex ${nonNullList[0].source.id}")
        databaseManager.articlesDao().addArticles(nonNullList)
    }

}