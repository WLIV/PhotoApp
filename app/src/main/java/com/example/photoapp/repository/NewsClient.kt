package com.example.photoapp.repository

import com.example.photoapp.api.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsClient {
    companion object {
        private val instance: NewsApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(NewsApi::class.java)
        }
        fun getClientInstance() : NewsApi = instance

    }
}