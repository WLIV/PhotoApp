package com.example.photoapp.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.photoapp.interfaces.PreferencesInterface
import com.example.photoapp.utils.Constants



class Preferences(val context: Context) : PreferencesInterface {
    private val sharedPrefsKey = "settingsSharedPrefs"
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE)
    private var editor : SharedPreferences.Editor = sharedPreferences.edit()

    override fun getSavedMinAmount(): Int {
        return sharedPreferences.getInt(Constants.minAmountKey, 3)
    }
    override fun getSavedMaxAmount() : Int{
        return sharedPreferences.getInt(Constants.maxAmountKey, 15)
    }

    override fun saveMinAmount( value: Int){
        editor.putInt(Constants.minAmountKey, value)
        editor.apply()
    }

    override fun saveMaxAmount( value: Int){
        editor.putInt(Constants.maxAmountKey, value)
        editor.apply()
    }
}