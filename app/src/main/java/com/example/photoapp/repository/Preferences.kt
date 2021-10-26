package com.example.photoapp.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.photoapp.interfaces.PreferencesInterface


class Preferences(val context: Context) : PreferencesInterface {
    private val sharedPrefsKey = "settingsSharedPrefs"

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE)
    private var editor : SharedPreferences.Editor = sharedPreferences.edit()


    //тут лучше переименовать просто в saveInt и getInt
    //потому что данный класс может быть переиспользован в других местах
    //константы пусть лежат в SettingsRepository, а не в классе Constants


    override fun getInt(key: String, defaultValue : Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }


    override fun saveInt( key : String, value: Int){
        editor.putInt(key, value)
        editor.apply()
    }
}