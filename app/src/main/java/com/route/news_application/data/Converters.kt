package com.route.news_application.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.route.news_application.data.models.SourceInArticles

class Converters {

    @TypeConverter
    fun fromSource(source: SourceInArticles?): String? {
        // Convert SourceInArticles object to a String representation
        // Implement logic to serialize the object to a String
        // For example, you can convert it to JSON using Gson
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): SourceInArticles? {
        // Convert String representation back to a SourceInArticles object
        // Implement logic to deserialize the String to an object
        // For example, you can parse JSON using Gson
        return Gson().fromJson(sourceString, SourceInArticles::class.java)
    }
}