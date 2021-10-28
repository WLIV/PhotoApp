package com.example.photoapp.interfaces

interface SettingsRepository {

    fun putMinPhotosAmount(amount: Int)
    fun putMaxPhotosAmount(amount: Int)

    fun getMinPhotosAmount(): Int
    fun getMaxPhotosAmount(): Int

}