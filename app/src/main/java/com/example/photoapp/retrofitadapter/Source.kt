package com.example.photoapp.retrofitadapter

//todo все поля, которые возвращаются с сервера, должны быть nullable
data class Source (
    val id: String? = null,
    val name: String
)