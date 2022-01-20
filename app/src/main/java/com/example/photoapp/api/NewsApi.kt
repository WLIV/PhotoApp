package com.example.photoapp.api

import com.example.photoapp.BuildConfig
import com.example.photoapp.retrofitadapter.Article
import com.example.photoapp.retrofitadapter.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(BuildConfig.NEWS_URL)
    suspend fun getNews() : News

}