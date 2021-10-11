package com.example.photoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.photoapp.adapters.ImageViewAdapter
import com.example.photoapp.interfaces.PhotoViewInterface
import com.example.photoapp.viewmodels.PhotoActivityViewModel

class PhotoActivity : AppCompatActivity(), PhotoViewInterface {
    //todo делай как можно меньше глобальных полей, viewPager явно лишний
    private lateinit var viewPager : ViewPager
    private lateinit var pagerAdapter : ImageViewAdapter
    private lateinit var viewModel : PhotoActivityViewModel

    //todo не нужно
    private var urlList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        viewModel = ViewModelProvider(this)[PhotoActivityViewModel::class.java]
        pagerAdapter = ImageViewAdapter(this, urlList)
        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter = pagerAdapter
        viewPager.isVisible = true //todo это лишнее

        viewModel.getUrls().observe(this, Observer {
            setImages(it)
        })


    }

    override fun setImages(urlList: List<String>) {
        pagerAdapter.setImages(urlList)
    }
}