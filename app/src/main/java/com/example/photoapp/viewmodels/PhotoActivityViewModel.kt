package com.example.photoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.repository.ImageLinkStorage

class PhotoActivityViewModel : ViewModel(){
    private var liveDataListUrls = MutableLiveData<ArrayList<String>>()

    public fun init(){
        val repository = ImageLinkStorage.getInstance()
        liveDataListUrls = repository.getImageUrls()
    }

    public fun getUrls() : LiveData<ArrayList<String>>{
        return liveDataListUrls
    }
}