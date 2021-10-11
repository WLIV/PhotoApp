package com.example.photoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoapp.interfaces.ImageRepository
import com.example.photoapp.repository.ImageLinkStorage

class PhotoActivityViewModel : ViewModel(){

    //не привязываемся к конкретной реализации репозитория, используем интерфейс
    //втрой принцип SOLID
    private val repository: ImageRepository = ImageLinkStorage()

    //в лайвдату в качестве параметра конструктора можно передавать дефолтное значение
    private var liveDataListUrls = MutableLiveData(
        repository.getImages(
            (3..15).random()
        )
    )

    //todo можно переделать как выше
//    public fun init(){
//        val repository = ImageLinkStorage.getInstance()
//        liveDataListUrls = repository.getImageUrls()
//    }

    //наружу отдаем List, а не ArrayList, как было раньше
    //потому что лист - это интерфейс, а arraylist - класс. Опять же второй принцип солид
    //todo в котлине не нужно писать паблик, это модификатор по умолчанию
    public fun getUrls() : LiveData<List<String>>{
        return liveDataListUrls
    }
}