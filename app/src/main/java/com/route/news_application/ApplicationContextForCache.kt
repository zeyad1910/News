package com.route.news_application

import android.app.Application
import android.content.Context

class ApplicationContextForCache :Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }
    companion object{
        lateinit var application : ApplicationContextForCache
            private set
        fun getApplicationContext() : Context = application.applicationContext
    }
}