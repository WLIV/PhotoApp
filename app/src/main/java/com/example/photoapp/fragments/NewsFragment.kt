package com.example.photoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapp.R
import com.example.photoapp.adapters.ArticleListItemConverter
import com.example.photoapp.adapters.RecyclerViewAdapter
import com.example.photoapp.viewmodels.NewsFragmentViewModel


class NewsFragment : Fragment() {

    private lateinit var newsView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       newsView = inflater.inflate(R.layout.fragment_news, container, false)
        val viewModel : NewsFragmentViewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]
        val recyclerView = newsView.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerViewAdapter = RecyclerViewAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = recyclerViewAdapter
        viewModel.getArticles().observe(viewLifecycleOwner, {
            //todo почему лайвдата отдает nullable список?
            //todo нужно чтобы лайвдата сразу отдавала сконверченные модели
            val articleList = ArticleListItemConverter(it).convertArticle()
            recyclerViewAdapter.setArticleList(articleList)
            })
        viewModel.getProgressBarStatus().observe(viewLifecycleOwner, {
            //todo так проще
            //скрываешь вью, если getProgressBarStatus == true
            //хотя я бы еще переименовал getProgressBarStatus в hideStatusBar, так понятнее
            //progressBar.isGone = it
            if (it){
                hideProgressBar()
            }
        })

        viewModel.getErrorMessage().observe(viewLifecycleOwner, {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        return newsView
    }

    private fun hideProgressBar(){
        val progressBar = newsView.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE
    }


    }
