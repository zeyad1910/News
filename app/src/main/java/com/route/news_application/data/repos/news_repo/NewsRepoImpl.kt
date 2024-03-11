package com.route.news_application.data.repos.news_repo

import com.route.news_application.models.Articles
import com.route.news_application.models.SourcesResponse

class NewsRepoImpl : NewsRepo {
    override fun loadSources(category: String) : SourcesResponse {
        TODO("Not yet implemented")
    }

    override fun loadArticles(sourceId: String) : Articles {
        TODO("Not yet implemented")
    }

    override fun searchViewWithCall(sourceId: String, query: String?) : Articles{
        TODO("Not yet implemented")
    }

}