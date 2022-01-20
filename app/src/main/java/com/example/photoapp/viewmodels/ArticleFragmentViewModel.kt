package com.example.photoapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.photoapp.repository.ArticleClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ArticleFragmentViewModel(app : Application) : AndroidViewModel(app) {
    private var callResult : com.example.photoapp.articleapiadapter.ArticleApiModel? = null

    private val articleLiveData = MutableLiveData<com.example.photoapp.retrofitadapter.Article?>()

    private val errorLiveData = MutableLiveData<String>()


    fun getArticle(url : String) : MutableLiveData<com.example.photoapp.retrofitadapter.Article?>{
        CoroutineScope(Dispatchers.IO).launch{loadArticle(url)}
        return articleLiveData
    }

    fun getErrorLiveData() : LiveData<String>{
        return errorLiveData
    }

    private suspend fun loadArticle(url: String) {
        val client = ArticleClient.getArticleClientInstance()

        callResult = try {
            client.getArticle(url,
                "extract-news.p.rapidapi.com",
                "1f6e800a3cmsh19a296b18eb4bf5p186926jsn7893289f2bd9")
        }catch (e : Exception){
            errorLiveData.postValue(e.toString())
            null
        }
        articleLiveData.postValue(callResult?.article)
    }



}


    
