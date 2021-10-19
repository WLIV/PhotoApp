package com.example.photoapp.retrofitadapter

//todo все поля, которые возвращаются с сервера, должны быть nullable
data class Article (
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String? = null
)