package com.example.photoapp.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
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
import androidx.constraintlayout.widget.ConstraintSet.GONE
import androidx.core.view.get
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.photoapp.adapters.OnArticleClick
import com.google.android.material.appbar.AppBarLayout


class NewsFragment : Fragment() {

    private lateinit var newsView: View
    private var recyclerView : RecyclerView? = null
    private var topArticleTitle : TextView? = null
    private var topArticleDescription : TextView? = null
    private var topArticleImage : ImageView? = null
    private var topArticleSection : AppBarLayout? = null
    private var topArticleMark : TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsView = inflater.inflate(R.layout.fragment_news, container, false)

        val viewModel: NewsFragmentViewModel =
            ViewModelProvider(this)[NewsFragmentViewModel::class.java]



        topArticleSection = newsView.findViewById(R.id.top_article_section)

        topArticleTitle = newsView.findViewById(R.id.top_article_title)

        topArticleMark = newsView.findViewById(R.id.top_article_mark)

       topArticleDescription = newsView.findViewById(R.id.top_article_description)

        topArticleImage = newsView.findViewById(R.id.top_article_image)


        recyclerView = newsView.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerViewAdapter = RecyclerViewAdapter(requireContext(), viewModel)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        context?.getDrawable(R.drawable.recyclerview_divider)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        recyclerView?.addItemDecoration(dividerItemDecoration)
        recyclerView?.adapter = recyclerViewAdapter
        var newRVState : Int? = null
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                newRVState = newState

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy>0 && newRVState == 2){
                    hideTopArticle()
                }
                if (!recyclerView.canScrollVertically(-1)){
                    showTopArticle()
                }

            }
        })
        viewModel.getClickedArticle().observe(viewLifecycleOwner, {
            if (it != null)
            {
                val action : NavDirections = NewsFragmentDirections.actionNewsFragmentToArticleFragment2(it)
                Navigation.findNavController(newsView).navigate(action)

            }
        })

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

        topArticleSection?.setOnClickListener(){
            viewModel.onArticleClick(-1)
        }

        return newsView
    }



    private fun hideTopArticle() {
        topArticleSection?.visibility = View.GONE
    }

    private fun showTopArticle(){

        topArticleSection?.visibility = View.VISIBLE
    }

    private fun fillTopArticle(articleListItem: List<ArticleListItem>){
        if (articleListItem.isEmpty()){
            return
        }
        val article = articleListItem[0]
        topArticleTitle?.text = article.title
        topArticleDescription?.text = article.description
        topArticleImage?.let { Glide.with(this).load(article.urlToImage).placeholder(R.drawable.ic_image_not_found).into(it)}
        topArticleDescription?.isVisible = true
        topArticleTitle?.isVisible = true
        topArticleMark?.isVisible = true


    }



}
