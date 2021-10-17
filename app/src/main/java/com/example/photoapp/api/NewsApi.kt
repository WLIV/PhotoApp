package com.example.photoapp.api

import com.example.photoapp.repository.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("v2/top-headlines?country=ru&apiKey=17028bda87d74c4c969f1525e283c869")
    fun getNews() : Call<List<News>>
}