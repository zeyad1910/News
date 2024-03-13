package com.route.news_application.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.route.news_application.data.models.Source

@Dao
interface SourcesDao {
    @Insert
    suspend fun addSources(list: List<Source>)

    @Query("delete from sources where category = :category")
    suspend fun deleteOldList(category : String)
    @Query("select * from sources where category = :category")
    suspend fun getSources(category : String):List<Source?>
}