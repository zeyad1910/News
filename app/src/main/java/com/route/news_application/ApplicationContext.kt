package com.route.news_application

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.route.news_application.data.repos.data_sources.ConnectivityChecker

class ApplicationContext :Application() {
    override fun onCreate() {
        super.onCreate()
        ConnectivityChecker.context = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        application = this
    }
    companion object{
        lateinit var application : ApplicationContext
            private set
        fun getApplicationContext() : Context = application.applicationContext
    }
}