package com.example.photoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.photoapp.R
import com.example.photoapp.viewmodels.ArticleFragmentViewModel

class ArticleFragment : Fragment() {

    private var title : TextView? = null
    private var articleText1 : TextView? = null
    private var articleText2 : TextView? = null
    private var titleImage : ImageView? = null
    private var progressBar : ProgressBar? = null
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
        progressBar = articleView.findViewById(R.id.progressBar)



        viewModel.getArticle(article).observe(viewLifecycleOwner, {
            fetchArticle(it)
        })

        viewModel.getErrorLiveData().observe(viewLifecycleOwner, {
            displayErrorCode(it)
        })

        return articleView

    }

    private fun displayErrorCode(error : String){
        progressBar?.visibility = View.GONE
        articleText1?.text = getString(R.string.errorCode)+ " " + error
        articleText2?.text = getString(R.string.somethingWentWrong)
        articleText1?.visibility = View.VISIBLE
        articleText2?.visibility = View.VISIBLE
    }

    private fun fetchArticle(article : com.example.photoapp.retrofitadapter.Article?) {
        if(article != null) {
            title?.text = article.title
            Glide.with(context!!).load(article.top_image).into(titleImage!!)
            articleText1?.text = article.meta_description
            articleText2?.text = article.text
            progressBar?.visibility = View.GONE
            title?.visibility = View.VISIBLE
            titleImage?.visibility = View.VISIBLE
            articleText1?.visibility = View.VISIBLE
            articleText2?.visibility = View.VISIBLE

        }


    }

}