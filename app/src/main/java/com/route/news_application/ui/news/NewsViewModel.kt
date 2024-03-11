package com.route.news_application.ui.news

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.news_application.api.ApiManager
import com.route.news_application.data.repos.data_sources.local_datasource.LocalDataSourceImpl
import com.route.news_application.data.repos.data_sources.remote_datasource.RemoteDataSourceImpl
import com.route.news_application.data.repos.news_repo.NewsRepo
import com.route.news_application.data.repos.news_repo.NewsRepoImpl
import com.route.news_application.models.Articles
import com.route.news_application.models.Source
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val newsRepo : NewsRepo = NewsRepoImpl(RemoteDataSourceImpl(),LocalDataSourceImpl())
    var sourcesListLiveData : MutableLiveData<List<Source?>?> = MutableLiveData()
    var articlesListLiveData : MutableLiveData<List<Articles?>?> = MutableLiveData()
    var articlesBySearchLiveData : MutableLiveData<List<Articles?>?> = MutableLiveData()
    var progressViewVisibilityLiveData : MutableLiveData<Boolean> = MutableLiveData(false)
    var errorViewVisibilityBooleanLiveData : MutableLiveData<Boolean> = MutableLiveData()
    var errorViewVisibilityStringLiveData : MutableLiveData<String> = MutableLiveData()

     fun loadSources(category:String) {
         progressViewVisibilityLiveData.value = true
         errorViewVisibilityBooleanLiveData.value = false
         errorViewVisibilityStringLiveData.value = " "
         viewModelScope.launch {
             try {
                 val sourcesList = newsRepo.loadSources(category,ApiManager.apiKey)
                 sourcesListLiveData.value = sourcesList
                 progressViewVisibilityLiveData.value = false
             }
             catch (e:Exception){
                 progressViewVisibilityLiveData.value = false
                 errorViewVisibilityBooleanLiveData.value = true
                 errorViewVisibilityStringLiveData.value = e.message ?: "some thing wrong please try again"
             }
         }
    }
     fun loadArticles(sourceId : String){
         progressViewVisibilityLiveData.value=false
        errorViewVisibilityBooleanLiveData.value=false
        errorViewVisibilityStringLiveData.value = " "
         viewModelScope.launch {
             try {
                 progressViewVisibilityLiveData.value = false
                 val articlesList =
                     newsRepo.loadArticles(sourceId,ApiManager.apiKey)
                 articlesListLiveData.value = articlesList
             }
             catch (e:Exception){
                 progressViewVisibilityLiveData.value = false
                 errorViewVisibilityBooleanLiveData.value=true
                 errorViewVisibilityStringLiveData.value = e.localizedMessage ?: "some thing wrong please try again"
             }
         }
    }
     @RequiresApi(Build.VERSION_CODES.O)
     fun searchViewWithCall(sourceId : String, query:String?){
        progressViewVisibilityLiveData.value=true
        viewModelScope.launch {
            try{
                val articlesList =
                    newsRepo.searchViewWithCall(query!!,sourceId,ApiManager.apiKey)
                articlesBySearchLiveData.value = articlesList
                progressViewVisibilityLiveData.value=false
            }catch (e:Exception){
                progressViewVisibilityLiveData.value=false
                errorViewVisibilityBooleanLiveData.value = true
                errorViewVisibilityStringLiveData.value =
                    e.message ?: "some thing wrong please try again"
            }
        }
    }
}