package com.example.photoapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.photoapp.adapters.OnArticleClick
import com.example.photoapp.utils.ArticleContentParser
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.klinker.android.article.ArticleActivity
import xyz.klinker.android.article.ArticleUtils
import xyz.klinker.android.article.api.ArticleApi
import xyz.klinker.android.article.data.Article
import xyz.klinker.android.article.data.model.ArticleModel

class ArticleFragmentViewModel(app : Application) : AndroidViewModel(app) {
    val articleLiveData = MutableLiveData<Article>()


    fun getArticle(url : String) : MutableLiveData<Article>{
        loadArticle(url)
        return articleLiveData
    }

    private fun loadArticle(url: String) {
        val articleContentParser = ArticleContentParser()
        val articleUtils = ArticleUtils("a8f22ef295e404a24ffbc0a93d4850cb")
        val article = articleUtils.fetchArticle(getApplication(), url)
        article.content =  articleContentParser.parseContent(article)
        articleLiveData.value = article
    }
}