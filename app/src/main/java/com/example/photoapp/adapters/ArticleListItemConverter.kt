package com.example.photoapp.adapters

import com.example.photoapp.retrofitadapter.Article

class ArticleListItemConverter(val articleList : List<Article?> ) {
    fun convertArticle() : List<ArticleListItem> {
        val articleListItemList = mutableListOf<ArticleListItem>()
        if (articleList == null) {
            return articleListItemList
        }
        articleList.forEach {
            val articleListItem = ArticleListItem(it?.title, it?.description, it?.urlToImage)
            articleListItemList.add(articleListItem)
        }
        return articleListItemList
    }
}