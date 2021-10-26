package com.example.photoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.photoapp.repository.Preferences
import com.example.photoapp.repository.SettingsRepository

class SettingsFragmentViewModel(app: Application) : AndroidViewModel(app) {


    private val preferences = Preferences(getApplication())

    private val state = MutableLiveData(
        //тут передаем начальное значение экрана, которое будет отображаться при входе в него
        //не рекомендуется работать с префсами из главного потока, но пока что сойдет
        State(
            minPhotosAmount = getMinPrefs(),
            maxPhotosAmount = getMaxPrefs()
        )
    )

    private val currentState
        get() = state.value!!

    //показывает алерт с переданным текстом
    private val showAlertDialog = MutableLiveData<String>()

    fun observeState(lifecycleOwner: LifecycleOwner, observer: Observer<State>){
        state.observe(lifecycleOwner, observer)
    }

    fun observeAlertDialog(lifecycleOwner: LifecycleOwner, observer: Observer<String>){
        showAlertDialog.observe(lifecycleOwner, observer)
    }


    //лучше никогда не вызывай методы вью модели из жизненного цикла вью (onCreate, onStart и прочее)
    //особенно если они стучатся к серверу
    //потому что при переворачивании телефона они будут снова выполнены
    //используй блок init во вьюмодели


    //контекст можно получить с помощью AndroidViewModel
//    fun getPrefs(context: Context){
//        preferences = Preferences(context)
//    }

    //эти методы теперь приватны
    private fun getMinPrefs() : Int{
        return preferences.getInt(SettingsRepository.minAmountKey, 3)
    }
    private fun getMaxPrefs() : Int{
        return preferences.getInt(SettingsRepository.maxAmountKey, 15)
    }

    //======

    //метод, который вызывает фрагмент при изменении ползунка с мин. кол-вом фоток
    //todo проверка max > min, иначе - showAlertDialog с текстом
    fun onMinAmountChanged(min: Int){
        if (min > currentState.maxPhotosAmount)
        {state.value = currentState.copy()}
        else if(min == 0){
            preferences.saveInt(value = min + 1, key = SettingsRepository.maxAmountKey)
            state.value = currentState.copy(minPhotosAmount = min + 1)
        }
        else {
            preferences.saveInt(value = min, key = SettingsRepository.minAmountKey)
            state.value = currentState.copy(
                minPhotosAmount = min
            )
        }
    }


    fun onMaxAmountChanged(max: Int){
        if (max < currentState.minPhotosAmount)
        {state.value = currentState.copy()}
        else if(max == 0){
            preferences.saveInt(value = max + 1, key = SettingsRepository.maxAmountKey)
            state.value = currentState.copy(maxPhotosAmount = max + 1)
        }
        else{
            preferences.saveInt(value = max, key = SettingsRepository.maxAmountKey)
            state.value = currentState.copy(maxPhotosAmount = max)
        }
    }



    /**
     * State - класс, который описывает состояние экрана
     * Все поля класса должны быть val
     * Фрагмент просто отображает то что в состоянии, нет никакой логики
     */
    data class State(
        val minPhotosAmount: Int,
        val maxPhotosAmount: Int
    )


}