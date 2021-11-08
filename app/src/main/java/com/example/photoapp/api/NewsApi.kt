package com.example.photoapp.api

import com.example.photoapp.BuildConfig
import com.example.photoapp.retrofitadapter.Article
import com.example.photoapp.retrofitadapter.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    //параметры сделай через query
    @GET("top-headlines?country=ru&apiKey=17028bda87d74c4c969f1525e283c869")
    suspend fun getNews() : News

}