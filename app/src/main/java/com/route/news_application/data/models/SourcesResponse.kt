package com.route.news_application.data.models

data class SourcesResponse(
    val status : String?=null,
    val sources : List<Source?>,

    //to onFailureCase
    val code : String?=null,
    val message : String?=null
)
