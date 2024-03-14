package com.route.news_application.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sources")
data class Source(
    @PrimaryKey
    @ColumnInfo
    val id: String,
    @ColumnInfo
    val name: String?=null,
    val description: String?=null,
    val url: String?=null,
    @ColumnInfo
    val category: String?=null,
    val language: String?=null,
    val country: String?=null
)
