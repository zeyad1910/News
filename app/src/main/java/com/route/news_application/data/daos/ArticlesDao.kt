package com.route.news_application.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.ArticlesDB
import com.route.news_application.data.models.SourceInArticles

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(list: List<ArticlesDB>)

    @Query("select * from articles_table where source = :source")
    suspend fun getArticles(source: SourceInArticles):List<ArticlesDB?>

//    @Query("delete from articles_table where source = :source")
//    suspend fun deleteOldArticles(source: String)

}