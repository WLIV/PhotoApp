package com.example.photoapp.interfaces

interface ImageRepository {

    fun getImages(amount: Int): List<String>

}