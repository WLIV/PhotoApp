package com.example.photoapp.repository

//todo можешь использовать этот класс для реализации SettingsRepository,
// только переименуй в SettingsRepositoryImpl
// ключи вынеси из companion object и сделай приватными
class SettingsRepository {
    companion object{
        const val maxAmountKey = "MAX_PHOTOS_AMOUNT"
        const val minAmountKey = "MIN_PHOTOS_AMOUNT"
    }
}