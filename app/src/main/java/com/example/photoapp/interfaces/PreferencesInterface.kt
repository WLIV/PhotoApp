package com.example.photoapp.interfaces

interface PreferencesInterface {
    fun getInt(key: String, defaultValue : Int): Int
    fun saveInt(key : String, value: Int)
}