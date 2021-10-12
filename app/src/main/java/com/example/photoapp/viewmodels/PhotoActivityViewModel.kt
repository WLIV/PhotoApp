package com.example.photoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.interfaces.ImageRepository
import com.example.photoapp.repository.ImageLinkStorage

class PhotoActivityViewModel : ViewModel(){


    private val repository: ImageRepository = ImageLinkStorage()


    private var liveDataListUrls = MutableLiveData(
        repository.getImages(
            (3..15).random()
        )
    )

    fun getUrls() : LiveData<List<String>>{
        return liveDataListUrls
    }
}