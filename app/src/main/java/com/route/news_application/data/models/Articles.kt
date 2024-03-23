package com.route.news_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.route.news_application.data.Converters

data class Articles(
    @PrimaryKey
    @ColumnInfo
    @TypeConverters(Converters::class)
    val source: SourceInArticles,
    @ColumnInfo
    val author: String? = null,
    @ColumnInfo
    val title: String? = null,
    @ColumnInfo
    val description: String? = null,
    @ColumnInfo
    val url: String? = null,
    @ColumnInfo
    val urlToImage: String,
    @ColumnInfo
    val publishedAt: String? = null,
    @ColumnInfo
    val content: String? = null
) {

    fun toArticleDB(): ArticlesDB {
        this.apply {
            return ArticlesDB(
                0,
                source,
                author,
                title,
                description,
                url,
                urlToImage,
                publishedAt,
                content
            )
        }
    }
}

@Entity(tableName = "articles_table")
data class ArticlesDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    @TypeConverters(Converters::class)
    val source: SourceInArticles,
    @ColumnInfo
    val author: String? = null,
    @ColumnInfo
    val title: String? = null,
    @ColumnInfo
    val description: String? = null,
    @ColumnInfo
    val url: String? = null,
    @ColumnInfo
    val urlToImage: String,
    @ColumnInfo
    val publishedAt: String? = null,
    @ColumnInfo
    val content: String? = null
) {

    fun toArticle(): Articles {
        this.apply {
            return Articles(
                source,
                author,
                title,
                description,
                url,
                urlToImage,
                publishedAt,
                content
            )
        }
    }
}