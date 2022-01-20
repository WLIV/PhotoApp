package com.example.photoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.photoapp.api.ArticleApi
import com.example.photoapp.api.NewsApi
import com.example.photoapp.retrofitadapter.Article
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ArticleClient {
    companion object {
        private val instance: ArticleApi by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://extract-news.p.rapidapi.com/v0/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ArticleApi::class.java)
        }
        fun getArticleClientInstance() : ArticleApi = ArticleClient.instance
    }
}
