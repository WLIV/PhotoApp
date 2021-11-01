package com.example.photoapp.repository

import android.content.Context
import com.example.photoapp.interfaces.SettingsRepository

//никогда не называй интерфейс и реализацию одинаково. К реализации добавляй в конце Impl
class SettingsRepository(val context: Context) :  SettingsRepository {
    private val preferences = Preferences(context)
    private val maxAmountKey = "MAX_PHOTOS_AMOUNT"
    private val minAmountKey = "MIN_PHOTOS_AMOUNT"

    override fun putMinPhotosAmount(amount: Int) {
        preferences.saveInt(minAmountKey, amount)
    }

    override fun putMaxPhotosAmount(amount: Int) {
        preferences.saveInt(maxAmountKey, amount)
    }

    override fun getMinPhotosAmount(): Int {
        return preferences.getInt(minAmountKey, 3)
    }

    override fun getMaxPhotosAmount(): Int {
        return preferences.getInt(maxAmountKey, 15)
    }

}