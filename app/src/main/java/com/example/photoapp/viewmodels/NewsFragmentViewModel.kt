package com.example.photoapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.api.NewsApi
import com.example.photoapp.retrofitadapter.Article
import com.example.photoapp.retrofitadapter.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsFragmentViewModel : ViewModel() {

    private val liveData = MutableLiveData<List<Article>>(emptyList())


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newsApi : NewsApi = retrofit.create(NewsApi::class.java)

        val call : Call<News> = newsApi.getNews()

       call.enqueue(object : Callback<News>{
           override fun onResponse(call: Call<News>, response: Response<News>) {
               val news = response.body()
               liveData.value = news?.articles.orEmpty()
           }

           override fun onFailure(call: Call<News>, t: Throwable) {
               Log.e("NewsFragmentViewModel", "ERROR", t)
           }

       })

    }




    fun getArticles() : LiveData<List<Article>?> {
        return liveData
    }
}