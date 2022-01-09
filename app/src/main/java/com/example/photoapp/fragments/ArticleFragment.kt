package com.example.photoapp.fragments

import android.content.UriMatcher
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.UrlUriLoader
import com.example.photoapp.R
import com.example.photoapp.viewmodels.ArticleFragmentViewModel
import com.example.photoapp.viewmodels.NewsFragmentViewModel
import com.google.gson.Gson
import xyz.klinker.android.article.ArticleIntent
import xyz.klinker.android.article.ArticleUtils
import xyz.klinker.android.article.api.ArticleApi
import xyz.klinker.android.article.data.Article

class ArticleFragment : Fragment() {

    private var title : TextView? = null
    private var articleText1 : TextView? = null
    private var articleText2 : TextView? = null
    private var titleImage : ImageView? = null
    private val viewModel : ArticleFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val articleView = inflater.inflate(R.layout.fragment_article, container, false)
        val articleBundle : ArticleFragmentArgs by navArgs()
        val article : String = articleBundle.clickedArticleUrl

        title = articleView.findViewById(R.id.article_page_title)
        articleText1 = articleView.findViewById(R.id.article_page_text1)
        articleText2 = articleView.findViewById(R.id.article_page_text2)
        titleImage = articleView.findViewById(R.id.article_page_image)


        viewModel.getArticle(article).observe(viewLifecycleOwner, {
            fetchArticle(it)
        })

        return articleView

    }

    private fun fetchArticle(article : Article) {
        title?.text = article.title
        Glide.with(context!!).load(article.image).into(titleImage!!)
        articleText1?.text = article.description
        articleText2?.text = article.content


    }

}