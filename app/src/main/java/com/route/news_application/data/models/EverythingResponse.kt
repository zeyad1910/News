package com.route.news_application.data.models

data class EverythingResponse(
    val status : String?=null,
    val totalResults : Int?=null,
    val articles : List<Articles?>,
//    //to onFailureCase
//    val code : String?=null,
//    val message : String?=null
)
