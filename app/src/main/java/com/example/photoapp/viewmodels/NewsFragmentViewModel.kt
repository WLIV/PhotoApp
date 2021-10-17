package com.example.photoapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.api.NewsApi
import com.example.photoapp.repository.Article
import com.example.photoapp.repository.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response


class NewsFragmentViewModel : ViewModel() {
    private var articleList : List<Article>? = mutableListOf<Article>()
    private val liveData = MutableLiveData(articleList)


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newsApi : NewsApi = retrofit.create(NewsApi::class.java)

        val call : Call<List<News>> = newsApi.getNews()

       call.enqueue(object : Callback<List<News>>{
           override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
               val news = response.body()
               articleList = news?.get(0)?.articles
           }

           override fun onFailure(call: Call<List<News>>, t: Throwable) {
               println(t.message)
           }

       })

    }




    fun getArticles() : MutableLiveData<List<Article>?> {
        return liveData
    }
}