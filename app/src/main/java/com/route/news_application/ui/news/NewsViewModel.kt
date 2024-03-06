package com.route.news_application.ui.news

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_application.api.ApiManager
import com.route.news_application.api.models.Articles
import com.route.news_application.api.models.EverythingResponse
import com.route.news_application.api.models.Source
import com.route.news_application.api.models.SourcesResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    var sourcesListLiveData : MutableLiveData<List<Source?>?> = MutableLiveData()
    var articlesListLiveData : MutableLiveData<List<Articles?>?> = MutableLiveData()
    var articlesBySearchLiveData : MutableLiveData<List<Articles?>?> = MutableLiveData()
    var progressViewVisibilityLiveData : MutableLiveData<Boolean> = MutableLiveData(false)
    var errorViewVisibilityBooleanLiveData : MutableLiveData<Boolean> = MutableLiveData()
    var errorViewVisibilityStringLiveData : MutableLiveData<String> = MutableLiveData()


     fun loadSources(category:String) {
         progressViewVisibilityLiveData.value = true
         errorViewVisibilityBooleanLiveData.value = false
         errorViewVisibilityStringLiveData.value = ""
         viewModelScope.launch {
             try {
                 val sourcesResponse =
                     ApiManager.service()?.getSources(category, ApiManager.apiKey)
                 sourcesListLiveData.value = sourcesResponse?.sources
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
                 val articlesResponse =
                     ApiManager.service()?.getEverything(sourceId, ApiManager.apiKey)
                 articlesListLiveData.value = articlesResponse?.articles
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
                val articlesResponse =
                    ApiManager.service()?.getEverythingForSearch(query,sourceId,ApiManager.apiKey)
                articlesBySearchLiveData.value = articlesResponse?.articles
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