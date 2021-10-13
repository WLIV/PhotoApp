package com.example.photoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.photoapp.R
import com.example.photoapp.adapters.ImageViewAdapter
import com.example.photoapp.viewmodels.PhotoFragmentViewModel


class PhotoFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val photoFragmentView = inflater.inflate(R.layout.fragment_photo, container, false)

        val viewModel : PhotoFragmentViewModel = ViewModelProvider(this)[PhotoFragmentViewModel::class.java]
        val pagerAdapter = ImageViewAdapter(requireContext())
        val viewPager : ViewPager = photoFragmentView.findViewById(R.id.viewpager)
        viewPager.adapter = pagerAdapter

        viewModel.getUrls().observe(viewLifecycleOwner, Observer {
            pagerAdapter.setImages(it)
        })
        return photoFragmentView
    }


}