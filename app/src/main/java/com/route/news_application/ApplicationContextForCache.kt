package com.route.news_application

import android.app.Application
import android.app.UiModeManager.MODE_NIGHT_NO
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class ApplicationContextForCache :Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        application = this
    }
    companion object{
        lateinit var application : ApplicationContextForCache
            private set
        fun getApplicationContext() : Context = application.applicationContext
    }
}