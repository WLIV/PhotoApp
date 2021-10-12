package com.example.photoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.photoapp.adapters.ImageViewAdapter
import com.example.photoapp.viewmodels.PhotoActivityViewModel

class PhotoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val viewModel : PhotoActivityViewModel = ViewModelProvider(this)[PhotoActivityViewModel::class.java]
        val pagerAdapter = ImageViewAdapter(this)
        val viewPager : ViewPager = findViewById(R.id.viewpager)
        viewPager.adapter = pagerAdapter

        viewModel.getUrls().observe(this, Observer {
            pagerAdapter.setImages(it)
        })


    }

}