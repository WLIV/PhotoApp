package com.example.photoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapp.R
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
        viewModel.getArticles().observe(viewLifecycleOwner, {recyclerViewAdapter.setArticleList(it)})
        return newsView
    }


    }
