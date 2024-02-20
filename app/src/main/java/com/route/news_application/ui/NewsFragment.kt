package com.route.news_application.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.route.news_application.R
import com.route.news_application.api.ApiManager
import com.route.news_application.api.models.EverythingResponse
import com.route.news_application.api.models.Source
import com.route.news_application.api.models.SourcesResponse
import com.route.news_application.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    lateinit var binding : FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiManager.service()?.getSources(ApiManager.apiKey)?.
        enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>,
            ) {
                if(response.isSuccessful){
                    response.body()?.sources.let {
                        showTabs(it!!)
                    }
                }else{
                    val errorByJson=Gson()
                        .fromJson(response.errorBody()?.string(),SourcesResponse::class.java)
                    showError()
                }
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun showError() {
        TODO("Not yet implemented")
    }

    private fun showTabs(sources:List<Source?>) {
        sources.forEach {
            val singleTab = binding.tabLayout.newTab()
            singleTab.text = it?.name
            binding.tabLayout.addTab(singleTab)
        }
    }


}