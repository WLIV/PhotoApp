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
        photoBtn.setOnClickListener {
            Navigation.findNavController(fragmentView).navigate(R.id.action_firstFragment_to_photoFragment)
        }
        return fragmentView
    }


    }
