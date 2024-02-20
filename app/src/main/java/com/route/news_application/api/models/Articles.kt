package com.route.news_application.api.models

data class Articles(
    val source : List<SourceInArticles?>?=null,
    val author : String?=null,
    val title : String?=null,
    val description : String?=null,
    val url : String?=null,
    val urlToImage : String?=null,
    val publishedAt : String?=null,
    val content: String?=null
)