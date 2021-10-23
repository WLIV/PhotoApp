package com.example.photoapp.interfaces

interface PreferencesInterface {
    fun getSavedMinAmount(): Int
    fun getSavedMaxAmount() : Int
    fun saveMinAmount(value: Int)
    fun saveMaxAmount(value: Int)
}