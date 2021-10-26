package com.example.photoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.photoapp.repository.Preferences

class SettingsFragmentViewModel(app: Application) : AndroidViewModel(app) {

    //todo сдлеать settings repository
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
        return preferences.getSavedMinAmount()
    }
    private fun getMaxPrefs() : Int{
        return preferences.getSavedMaxAmount()
    }

    //======

    //метод, который вызывает фрагмент при изменении ползунка с мин. кол-вом фоток
    //todo проверка max > min, иначе - showAlertDialog с текстом
    fun onMinAmountChanged(min: Int){
        //сохраняем в префсы, обновляем состояние
        preferences.saveMinAmount(min)
        state.value = currentState.copy(
            minPhotosAmount = min
        )
    }

    //ну а тут с максимальным
    fun onMaxAmountChanged(max: Int){
        //todo
    }

//    fun saveMaxAmount(currentMaxAmount: Int) {
//        preferences.saveMaxAmount(currentMaxAmount)
//    }
//
//    fun saveMinAmount(currentMinAmount: Int) {
//        preferences.saveMinAmount(currentMinAmount)
//
//    }

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