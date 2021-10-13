package com.example.photoapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse


class NewsFragmentViewModel : ViewModel() {
    private var articleList = mutableListOf<Article>()
    private val liveData = MutableLiveData(articleList)
    private val newsRep = NewsApiClient("17028bda87d74c4c969f1525e283c869")

    init {

        newsRep.getEverything(
            EverythingRequest.Builder().q("crypto").build(),
            object : NewsApiClient.ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    liveData.value = response.articles


                }

                override fun onFailure(throwable: Throwable) {
                    println(throwable.message)
                }
            })
    }




    fun getArticles() : MutableLiveData<MutableList<Article>> {
        return liveData
    }
}