package com.example.photoapp.interfaces

//todo в MVVM не нужны интерфейсы для вью
interface PhotoViewInterface {
    fun setImages(urlList: List<String>)
}