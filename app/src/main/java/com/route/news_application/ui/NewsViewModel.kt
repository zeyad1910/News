package com.route.news_application.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.news_application.api.ApiManager
import com.route.news_application.api.models.Source
import com.route.news_application.api.models.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    var sourcesListLiveData : MutableLiveData<List<Source?>?> = MutableLiveData()
    var progressViewVisibilityLiveData : MutableLiveData<Boolean> = MutableLiveData(false)
    var errorViewVisibilityBooleanLiveData : MutableLiveData<Boolean> = MutableLiveData()
    var errorViewVisibilityStringLiveData : MutableLiveData<String> = MutableLiveData()

     fun loadSources(category:String) {
         progressViewVisibilityLiveData.value = true
         errorViewVisibilityBooleanLiveData.value = false
         errorViewVisibilityStringLiveData.value = ""
        ApiManager.service()?.getSources(category, ApiManager.apiKey)?.
        enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>,
            ) {
                progressViewVisibilityLiveData.value = false
                if(response.isSuccessful){
                    response.body()?.sources.let {
                        sourcesListLiveData.value = it
                    }
                }else{
                    val errorByJson= Gson()
                        .fromJson(response.errorBody()?.string(), SourcesResponse::class.java)
                    errorViewVisibilityBooleanLiveData.value = true
                    errorViewVisibilityStringLiveData.value = errorByJson.message ?: "some thing wrong please try again"
                }
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                progressViewVisibilityLiveData.value = false
                errorViewVisibilityBooleanLiveData.value = true
                errorViewVisibilityStringLiveData.value = t.message ?: "some thing wrong please try again"
            }

        })
    }
}