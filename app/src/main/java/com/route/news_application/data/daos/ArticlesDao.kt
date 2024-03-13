package com.route.news_application.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.route.news_application.data.models.Articles

//@Dao
//interface ArticlesDao {
//    @Insert
//    suspend fun addArticles(list: List<Articles?>)
//    @Query("select * from articles where source = :sourceId ")
//    suspend fun getArticles(sourceId : String):List<Articles?>
//}