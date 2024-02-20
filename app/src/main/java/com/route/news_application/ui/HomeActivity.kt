package com.route.news_application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.news_application.R
import com.route.news_application.databinding.ActivityHomeBinding
import okhttp3.Cache

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    val newsFragment = NewsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(newsFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_conrainer,fragment,"")
            .commit()
    }

}