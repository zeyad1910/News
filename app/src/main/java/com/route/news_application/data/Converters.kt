package com.route.news_application.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.route.news_application.data.models.SourceInArticles

class Converters {

    @TypeConverter
    fun fromSource(source: SourceInArticles?): String? {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): SourceInArticles? {
        return Gson().fromJson(sourceString, SourceInArticles::class.java)
    }
}