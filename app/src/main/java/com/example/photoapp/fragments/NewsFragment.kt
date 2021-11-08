package com.example.photoapp.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photoapp.R
import com.example.photoapp.adapters.ArticleListItem
import com.example.photoapp.adapters.RecyclerViewAdapter
import com.example.photoapp.viewmodels.NewsFragmentViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class NewsFragment : Fragment() {

    private lateinit var newsView: View

    private var topArticleTitle : TextView? = null
    private var topArticleDescription : TextView? = null
    private var topArticleImage : ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsView = inflater.inflate(R.layout.fragment_news, container, false)

        val viewModel: NewsFragmentViewModel =
            ViewModelProvider(this)[NewsFragmentViewModel::class.java]





        topArticleTitle = newsView.findViewById(R.id.top_article_title)

       topArticleDescription = newsView.findViewById(R.id.top_article_description)

        topArticleImage = newsView.findViewById(R.id.top_article_image)


        val recyclerView = newsView.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerViewAdapter = RecyclerViewAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        context?.getDrawable(R.drawable.recyclerview_divider)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = recyclerViewAdapter

        viewModel.getArticles().observe(viewLifecycleOwner, {
            recyclerViewAdapter.setArticleList(it)
            fillTopArticle(it)
        })
        viewModel.getProgressBarStatus().observe(viewLifecycleOwner, {
            val progressBar = newsView.findViewById<ProgressBar>(R.id.progressBar)
            progressBar.isGone = it

        })

        viewModel.getErrorMessage().observe(viewLifecycleOwner, {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        return newsView
    }

    private fun fillTopArticle(articleListItem: List<ArticleListItem>){
        if (articleListItem.isEmpty()){
            return
        }
        val article = articleListItem[0]
        topArticleTitle?.text = article.title
        topArticleDescription?.text = article.description
        topArticleImage?.let { Glide.with(this).load(article.urlToImage).placeholder(R.drawable.ic_image_not_found).into(it) }
        topArticleDescription?.isVisible = true
        topArticleTitle?.isVisible = true


    }

    }
