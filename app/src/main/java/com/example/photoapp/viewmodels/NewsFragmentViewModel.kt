package com.example.photoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


class NewsFragmentViewModel : ViewModel() {


    private val articles = MutableLiveData<List<ArticleListItem>>(emptyList())
    private val progressBarHidden = MutableLiveData(false)
    private val errorMessage = MutableLiveData<String?>(null)



    init {
        //todo у вью моделей есть прекрасная вещь как viewModelScope
        CoroutineScope(Dispatchers.IO).launch { getNews() }
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

    private suspend fun getNews() = coroutineScope {
        //todo launch запускает асинхронный блок кода. Enqueue делает тоже самое. Не надо их смешивать
        //newsClient.getNews() можно просто пометить как suspend и все, только оберни в try/catch
        launch {
            val newsClient = NewsClient.getClientInstance()

            val call: Call<News> = newsClient.getNews()

            call.enqueue(object : Callback<News> {
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

}