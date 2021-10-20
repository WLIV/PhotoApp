package com.example.photoapp.retrofitadapter

//todo null по дефолту - это лишнее
data class News (
    val status: String? = null,
    val totalResults: Long? = null,
    val articles: List<Article>? = null
) {



}


