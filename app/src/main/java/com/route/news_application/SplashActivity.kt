package com.route.news_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashToHome()
    }

    private fun splashToHome() {
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
            ,2000)
    }
}