package com.route.news_application.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.news_application.data.daos.SourcesDao
import com.route.news_application.data.models.Source

@Database(entities =  [Source::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase(){
    abstract fun sourcesDao() : SourcesDao
    //abstract fun articlesDao() : ArticlesDao

    companion object{
        private var databaseManager : DatabaseManager?=null

        fun init(context: Context){
            if(databaseManager==null) {
                synchronized(DatabaseManager::class.java) {
                    databaseManager = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseManager::class.java,
                        "news_database"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
        fun getInstance() : DatabaseManager {
            return databaseManager!!
        }
    }
}