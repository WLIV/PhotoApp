package com.example.photoapp.api

import com.example.photoapp.articleapiadapter.ArticleApiModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ArticleApi {
    @GET("article")
    suspend fun getArticle(@Query("url") url : String,
                   @Header("x-rapidapi-host") hostHeader : String,
                   @Header("x-rapidapi-key")  apiKey : String) : ArticleApiModel
}