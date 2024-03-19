package com.route.news_application.ui.news

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.route.news_application.Constants
import com.route.news_application.adapter.NewsAdapter
import com.route.news_application.data.models.Articles
import com.route.news_application.data.models.Source
import com.route.news_application.databinding.FragmentNewsBinding
import androidx.appcompat.widget.SearchView
import androidx.core.view.forEach
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.route.news_application.data.models.SourceInArticles
import com.route.news_application.ui.DetailsActivity

class NewsFragment(private val category:String) : Fragment() {
    lateinit var binding : FragmentNewsBinding
    val adapter = NewsAdapter(listOf())
    lateinit var newsViewModel : NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding = FragmentNewsBinding.inflate(layoutInflater,container,false)
        binding.newsVM = newsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        prepareRV()
        initListener()
        sendDataToDetailsActivity()
        observeLiveData()
    }
    private fun observeLiveData(){
        newsViewModel.loadSources(category)
        newsViewModel.sourcesListLiveData.observe(viewLifecycleOwner,object :  Observer<List<Source?>?>{
            override fun onChanged(t: List<Source?>?) {
                showTabs(t!!)
            }
        })
        newsViewModel.errorViewVisibilityStringLiveData.observe(viewLifecycleOwner){ it ->
            binding.errorContent.errorTxt.text=it!!
        }
        newsViewModel.articlesListLiveData.observe(viewLifecycleOwner){
            adapter.updateList(it!!)
            Log.e("test to show articles list ","$it")
        }
        newsViewModel.articlesBySearchLiveData.observe(viewLifecycleOwner){
            adapter.updateList(it!!)
            Log.e("test to show articles list ","$it")
        }
    }
    private fun showTabs(sources:List<Source?>) {
        sources.forEach {
            it?.let {
                val singleTab = binding.tabLayout.newTab()
                singleTab.text = it.name
                singleTab.tag = it
                binding.tabLayout.addTab(singleTab)
                tabMargin()
            }
        }
    }
    private fun initListener(){
        binding.errorContent.retryBtn.setOnClickListener {
            newsViewModel.loadSources(category)
        }
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                source.id?.let {
                    val sourceInArticles = SourceInArticles(source.id, source.name)
                    Log.d("tt", "source $source")
                    newsViewModel.loadArticles(sourceInArticles)
                    binding.searchView.setQuery("",false) //to clear text
                    listenerForSearch(it)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                source.id?.let {
                    val sourceInArticles = SourceInArticles(source.id, source.name)
                    newsViewModel.loadArticles(sourceInArticles)
                    binding.searchView.setQuery("",false) //to clear text
                    listenerForSearch(it)
                }
            }

        })
    }
    private fun prepareRV(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter=adapter
    }
    private fun sendDataToDetailsActivity(){
        adapter.onItemViewClickListener = object : NewsAdapter.SetOnItemViewClickListener{
            override fun itemViewClickListener(data: Articles?, position: Int) {
                val intent = Intent(requireActivity(), DetailsActivity::class.java)
                intent.putExtra(Constants.IMAGE_KEY,data?.urlToImage)
                intent.putExtra(Constants.AUTHOR_KEY,data?.author)
                intent.putExtra(Constants.TITLE_KEY,data?.title)
                intent.putExtra(Constants.PUBLISHAT_KEY,data?.publishedAt)
                intent.putExtra(Constants.DESCRIPTION_KEY,data?.description)
                intent.putExtra(Constants.URL_KEY,data?.url)
                startActivity(intent)
            }
        }
    }
    private fun listenerForSearch(sourceId: String){
        binding.searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextSubmit(query: String?): Boolean {
                newsViewModel.searchViewWithCall(sourceId,query)
                return true
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextChange(newText: String?): Boolean {
                newsViewModel.searchViewWithCall(sourceId,newText)
                return true
            }

        })
    }

    private fun tabMargin() {
        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        tabs.forEach {
            val layoutParams = it.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = 20
            it.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }
}