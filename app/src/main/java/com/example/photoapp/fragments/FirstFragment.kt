package com.example.photoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.photoapp.R

class FirstFragment : Fragment() {

    private lateinit var  fragmentView : View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       fragmentView = inflater.inflate(R.layout.fragment_first, container, false)
        val photoBtn = fragmentView.findViewById<Button>(R.id.photoBtn)
        photoBtn.setOnClickListener { Navigation.findNavController(fragmentView).navigate(R.id.action_firstFragment_to_photoFragment) }
        val newsBtn = fragmentView.findViewById<Button>(R.id.newsBtn)
        newsBtn.setOnClickListener { Navigation.findNavController(fragmentView).navigate(R.id.action_firstFragment_to_newsFragment) }
        val settingsButton = fragmentView.findViewById<Button>(R.id.settingsButton)
        settingsButton.setOnClickListener { Navigation.findNavController(fragmentView).navigate(R.id.action_firstFragment_to_settingsFragment) }
        return fragmentView
    }


    }
