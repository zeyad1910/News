package com.route.news_application.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.route.news_application.R
import com.route.news_application.adapter.NewsAdapter
import com.route.news_application.api.ApiManager
import com.route.news_application.api.models.Articles
import com.route.news_application.api.models.EverythingResponse
import com.route.news_application.api.models.Source
import com.route.news_application.api.models.SourcesResponse
import com.route.news_application.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    lateinit var binding : FragmentNewsBinding
    val adapter = NewsAdapter(listOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSources()
        prepareRV()
        initListener()
        sendDataToDetailsActivity()
    }

    private fun loadSources() {
        checkProgressViewVisibility(true)
        checkErrorViewVisibility(false,"")
        ApiManager.service()?.getSources(ApiManager.apiKey)?.
        enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>,
            ) {
                checkProgressViewVisibility(false)
                if(response.isSuccessful){
                    response.body()?.sources.let {
                        showTabs(it!!)
                    }
                }else{
                    val errorByJson=Gson()
                        .fromJson(response.errorBody()?.string(),SourcesResponse::class.java)
                    checkErrorViewVisibility(true,errorByJson.message ?:
                    "some thing wrong please try again ")
                }
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                checkProgressViewVisibility(false)
                checkErrorViewVisibility(true,t.message ?:
                "some thing wrong please try again ")
            }

        })
    }

    private fun showTabs(sources:List<Source?>) {
        sources.forEach {
            val singleTab = binding.tabLayout.newTab()
            singleTab.text = it?.name
            singleTab.tag = it
            binding.tabLayout.addTab(singleTab)
        }

    }

    private fun checkErrorViewVisibility(isVisible:Boolean,message:String){
        binding.errorContent.root.isVisible = isVisible
        binding.errorContent.errorTxt.text = message
    }
    private fun checkProgressViewVisibility(isVisible:Boolean){
        binding.errorProgress.isVisible = isVisible
    }
    private fun initListener(){
        binding.errorContent.retryBtn.setOnClickListener {
            loadSources()
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                source.id?.let {
                    loadArticles(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                source.id?.let {
                    loadArticles(it)
                }
            }

        })
    }

    private fun loadArticles(sourceId : String){
        checkProgressViewVisibility(false)
        checkErrorViewVisibility(false, "")
       ApiManager.service()?.getEverything(sourceId,ApiManager.apiKey)
           ?.enqueue(object :Callback<EverythingResponse>{
               override fun onResponse(
                   call: Call<EverythingResponse>,
                   response: Response<EverythingResponse>,
               ) {
                   if(response.isSuccessful) {
                       response.body()?.articles.let {
                           adapter.updateList(it!!)
                       }
                   }else{
                       val error =
                           Gson().fromJson(response.errorBody()?.string(),EverythingResponse::class.java)
                       checkErrorViewVisibility(true, error.status ?: " some thing wrong")
                   }
               }

               override fun onFailure(call: Call<EverythingResponse>, t: Throwable) {
                   checkProgressViewVisibility(false)
                   checkErrorViewVisibility(true,t.message ?:
                   "some thing wrong please try again ")
               }

           } )
    }

    private fun prepareRV(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter=adapter
    }

    private fun sendDataToDetailsActivity(){
        adapter.onItemViewClickListener = object : NewsAdapter.SetOnItemViewClickListener{
            override fun itemViewClickListener(data: Articles?, position: Int) {
                val intent = Intent(requireActivity(),DetailsActivity::class.java)
                startActivity(intent)
            }

        }
    }
}