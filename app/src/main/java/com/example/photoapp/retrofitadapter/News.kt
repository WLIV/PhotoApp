package com.example.photoapp.retrofitadapter

import com.beust.klaxon.Klaxon


//todo все поля, которые возвращаются с сервера, должны быть nullable
data class News (
    val status: String,
    val totalResults: Long,
    val articles: List<Article>
) {
    //todo klaxon не нежен, ты используешь Gson (смотри инициализацию ретрофита)
    private val klaxon = Klaxon()
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        private val klaxon = Klaxon()
        public fun fromJson(json: String) = klaxon.parse<News>(json)
    }




}


