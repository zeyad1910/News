package com.route.news_application.ui.news

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.news_application.api.ApiManager
import com.route.news_application.api.models.Articles
import com.route.news_application.api.models.EverythingResponse
import com.route.news_application.api.models.Source
import com.route.news_application.api.models.SourcesResponse
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
     fun loadArticles(sourceId : String){

//        checkProgressViewVisibility(false)
         progressViewVisibilityLiveData.value=false
//        checkErrorViewVisibility(false, "")
        errorViewVisibilityBooleanLiveData.value=false
        errorViewVisibilityStringLiveData.value = " "
        ApiManager.service()?.getEverything(sourceId,ApiManager.apiKey)
            ?.enqueue(object :Callback<EverythingResponse>{
                override fun onResponse(
                    call: Call<EverythingResponse>,
                    response: Response<EverythingResponse>,
                ) {
                    if(response.isSuccessful) {
                        response.body()?.articles.let {
                            articlesListLiveData.value = it
//                            adapter.updateList(it!!)
                        }
                    }else{
                        val error =
                            Gson().fromJson(response.errorBody()?.string(), EverythingResponse::class.java)
//                       checkErrorViewVisibility(true, error.status ?: " some thing wrong")
                        errorViewVisibilityBooleanLiveData.value=true
                        errorViewVisibilityStringLiveData.value = error.status ?: "some thing wrong"
                    }
                }

                override fun onFailure(call: Call<EverythingResponse>, t: Throwable) {
//                   checkProgressViewVisibility(false)
                    progressViewVisibilityLiveData.value = false
//                   checkErrorViewVisibility(true,t.message ?:
//                   "some thing wrong please try again ")
                    errorViewVisibilityBooleanLiveData.value=true
                    errorViewVisibilityStringLiveData.value = t.localizedMessage ?: "some thing wrong please try again"
                }

            } )
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun searchViewWithCall(sourceId : String,query:String?){
//        checkProgressViewVisibility(false)
//        checkErrorViewVisibility(false, "")
        ApiManager.service()?.getEverythingForSearch(query,sourceId,ApiManager.apiKey)
            ?.enqueue(object:Callback<EverythingResponse>{
                override fun onResponse(
                    call: Call<EverythingResponse>,
                    response: Response<EverythingResponse>,
                ) {
                    if(response.isSuccessful) {
                        response.body()?.articles.let {
                            articlesBySearchLiveData.value = it
//                            adapter.updateList(it!!)
                        }
                    }
                    else{
                        val error =
                            Gson().fromJson(
                                response.errorBody()?.string(),
                                EverythingResponse::class.java
                            )
                        errorViewVisibilityBooleanLiveData.value=true
                        errorViewVisibilityStringLiveData.value = error.status ?: "some thing wrong"
//                        checkErrorViewVisibility(true, error.status ?: " some thing wrong")
                    }
                }

                override fun onFailure(call: Call<EverythingResponse>, t: Throwable) {
//                    checkProgressViewVisibility(false)
//                    checkErrorViewVisibility(true,t.message ?:
//                    "some thing wrong please try again ")
                }

            })
    }
}