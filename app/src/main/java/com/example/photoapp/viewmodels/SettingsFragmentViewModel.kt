package com.example.photoapp.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.photoapp.R
import com.example.photoapp.repository.SettingsRepository
import com.example.photoapp.utils.SingleLiveEvent

class SettingsFragmentViewModel(app: Application) : AndroidViewModel(app) {



    private val settingsRepository : com.example.photoapp.interfaces.SettingsRepository = SettingsRepository(getApplication())

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
    private val showAlertDialog = SingleLiveEvent<String>()

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
        return settingsRepository.getMinPhotosAmount()
    }
    private fun getMaxPrefs() : Int{
        return settingsRepository.getMaxPhotosAmount()
    }


    //метод, который вызывает фрагмент при изменении ползунка с мин. кол-вом фоток
    fun onMinAmountChanged(min: Int){
        when {
            min > currentState.maxPhotosAmount -> {
                state.value = currentState.copy()
                val context : Context = getApplication()
                showAlertDialog.value = context.getString(R.string.maxAmountError)

            }
            min == 0 -> {
                settingsRepository.putMinPhotosAmount(min + 1)
                state.value = currentState.copy(minPhotosAmount = min + 1)
            }
            else -> {
                settingsRepository.putMinPhotosAmount(min)
                state.value = currentState.copy(
                    minPhotosAmount = min
                )
            }
        }
    }


    fun onMaxAmountChanged(max: Int){

        when {
            currentState.minPhotosAmount > 1 && max < currentState.minPhotosAmount -> {
                state.value = currentState.copy()
                val context: Context = getApplication()
                showAlertDialog.value = context.getString(R.string.maxAmountError)

            }

            max == 0 -> {
                settingsRepository.putMaxPhotosAmount(max + 1)
                state.value = currentState.copy(maxPhotosAmount = max + 1)
            }
            else -> {
                settingsRepository.putMaxPhotosAmount(max)
                state.value = currentState.copy(maxPhotosAmount = max)
            }
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