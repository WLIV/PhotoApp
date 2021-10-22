package com.example.photoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.adapters.ArticleListItem
import com.example.photoapp.adapters.ArticleListItemConverter
import com.example.photoapp.repository.NewsClient
import com.example.photoapp.retrofitadapter.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragmentViewModel : ViewModel() {


    private val articles = MutableLiveData<List<ArticleListItem>>(emptyList())
    private val progressBarHidden = MutableLiveData(false)
    private val errorMessage = MutableLiveData<String?>(null)


    init {
        getNews() //там было написано про приватный метод
        //не стоит писать много кода в инит блоке, разделяй по фукциям
    }

    fun getErrorMessage() : LiveData<String?>{
        return errorMessage
    }

    //в таких случаях надо либо переименовывать в hideProgressBar
    //или писать комментарий к методу
    //иначе вообще не понятно что за статус с boolean значением
    fun getProgressBarStatus() : LiveData<Boolean>{
        return progressBarHidden
    }

    fun getArticles() : LiveData<List<ArticleListItem>> {
        return articles
    }

    private fun getNews(){
        val newsClient = NewsClient.getClientInstance()

        val call : Call<News> = newsClient.getNews()

        call.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()?.articles
                articles.value = news?.let { ArticleListItemConverter(it).convertArticle() }
                progressBarHidden.value = true
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                progressBarHidden.value = true
                errorMessage.value = t.message.toString()
                errorMessage.postValue(null)
            }
        })
    }

}