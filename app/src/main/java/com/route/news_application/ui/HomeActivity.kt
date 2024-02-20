package com.route.news_application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.news_application.api.ApiManager
import com.route.news_application.api.models.EverythingResponse
import com.route.news_application.api.models.SourcesResponse
import com.route.news_application.databinding.ActivityHomeBinding
import okhttp3.Cache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ApiManager.service()?.getSources("5726c7cc55fb426eb40713b9389af78b")?.
        enqueue(object : Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>,
                ) {
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        ApiManager.service()?.getEverything("apple","5726c7cc55fb426eb40713b9389af78b")
            ?.enqueue(object : Callback<EverythingResponse>{
                override fun onResponse(
                    call: Call<EverythingResponse>,
                    response: Response<EverythingResponse>,
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<EverythingResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
    }

    fun cache():Cache{
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val cache = Cache(applicationContext.cacheDir, cacheSize)
        return cache
    }
}