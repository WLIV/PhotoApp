package com.example.photoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photoapp.adapters.ArticleListItem
import com.example.photoapp.adapters.ArticleListItemConverter
import com.example.photoapp.repository.NewsClient
import com.example.photoapp.retrofitadapter.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class NewsFragmentViewModel : ViewModel() {


    private val articles = MutableLiveData<List<ArticleListItem>>(emptyList())
    private val progressBarHidden = MutableLiveData(false)
    private val errorMessage = MutableLiveData<String?>(null)



    init {
        viewModelScope.launch { getNews() }
    }


    fun getErrorMessage() : LiveData<String?>{
        return errorMessage
    }


    fun getProgressBarStatus() : LiveData<Boolean>{
        return progressBarHidden
    }

    fun getArticles() : LiveData<List<ArticleListItem>> {
        return articles
    }

    private suspend fun getNews() {
             val newsClient = NewsClient.getClientInstance()
             val callResult : News? = try {
                 newsClient.getNews()
             }catch (e : Exception){
                 errorMessage.value = e.toString()
                 println(e.toString())
                 errorMessage.postValue(null)
                 null
             }

       if (callResult != null){
           articles.value = callResult.articles?.let { ArticleListItemConverter(it).convertArticle() }
           progressBarHidden.value = true
       }
        else{
           progressBarHidden.value = true

       }

    }


    }

