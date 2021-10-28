package com.example.photoapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.interfaces.ImageRepository
import com.example.photoapp.repository.ImageLinkStorage
import com.example.photoapp.repository.Preferences
import com.example.photoapp.repository.SettingsRepository

class PhotoFragmentViewModel() : ViewModel(){

    private val repository: ImageRepository = ImageLinkStorage()


    private var liveDataListUrls = MutableLiveData<List<String>>(emptyList())

    fun getUrlsLiveData() : LiveData<List<String>>{
        return liveDataListUrls
    }

    fun getImagesWithPrefs(context: Context){
        val preferences = SettingsRepository(context)
        liveDataListUrls.value = repository.getImages(
            (preferences.getMinPhotosAmount()..preferences.getMaxPhotosAmount()).random()
        )
    }






}