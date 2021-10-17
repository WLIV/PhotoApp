package com.example.photoapp.repository

import com.google.gson.annotations.SerializedName

import com.beust.klaxon.*



data class News (
    val status: String,
    val totalResults: Long,
    val articles: List<Article>
) {
    private val klaxon = Klaxon()
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        private val klaxon = Klaxon()
        public fun fromJson(json: String) = klaxon.parse<News>(json)
    }




}


