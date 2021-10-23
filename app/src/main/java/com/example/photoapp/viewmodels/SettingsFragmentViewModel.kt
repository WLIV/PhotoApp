package com.example.photoapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.photoapp.repository.Preferences

class SettingsFragmentViewModel : ViewModel() {
    private lateinit var preferences: Preferences

    fun getPrefs(context: Context){
        preferences = Preferences(context)
    }
    fun getMinPrefs() : Int{
        return preferences.getSavedMinAmount()
    }
    fun getMaxPrefs() : Int{
        return preferences.getSavedMaxAmount()
    }

    fun saveMaxAmount(currentMaxAmount: Int) {
        preferences.saveMaxAmount(currentMaxAmount)
    }

    fun saveMinAmount(currentMinAmount: Int) {
        preferences.saveMinAmount(currentMinAmount)

    }


}