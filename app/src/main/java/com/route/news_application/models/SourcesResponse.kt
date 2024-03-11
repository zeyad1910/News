package com.route.news_application.models

data class SourcesResponse(
    val status : String?=null,
    val sources : List<Source?>?=null,

    //to onFailureCase
    val code : String?=null,
    val message : String?=null
)
