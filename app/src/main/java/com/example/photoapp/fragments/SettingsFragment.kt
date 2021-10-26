package com.example.photoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.photoapp.R
import com.example.photoapp.utils.SeekBarListener
import com.example.photoapp.viewmodels.SettingsFragmentViewModel


class SettingsFragment : Fragment() {

    private lateinit var fragmentView: View

    //получаем вьюмодель с помощью делешата (мажешь почитать об этом, если интересно
    private val viewModel: SettingsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_settings, container, false)

        //раньше состояние хранилось во вью и оно уничтожалось при смене конфигурации
        //спасало только то, что оно сохранялось в префсы
//        var currentMinAmount: Int = viewModel.getMinPrefs()
//        var currentMaxAmount: Int = viewModel.getMaxPrefs()

        val minTextView = fragmentView.findViewById<TextView>(R.id.chosenAmountMin)
        minTextView.text = currentMinAmount.toString()

        val maxTextView = fragmentView.findViewById<TextView>(R.id.chosenAmountMax)
        maxTextView.text = currentMaxAmount.toString()

        val minSeekBar: SeekBar = fragmentView.findViewById(R.id.minPhotoAmount)
        minSeekBar.progress = currentMinAmount

        minSeekBar.setOnSeekBarChangeListener(SeekBarListener {
            //тут просто говорим вьюмодели, что данные поменялись
            viewModel.onMinAmountChanged(it)
        })

        //todo для maxSeekBar

//        minSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
//                currentMinAmount = minSeekBar.progress
//                minTextView.text = currentMinAmount.toString()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar) {}
//            override fun onStopTrackingTouch(seekBar: SeekBar) {
//                if (currentMaxAmount < currentMinAmount) {
//                    minSeekBar.progress = viewModel.getMinPrefs()
//                    //todo вынеси AlertDialog в отдельный класс (отнаследуйся от него)
//                    val a_builder = AlertDialog.Builder(context!!)
//                    a_builder.setMessage(R.string.maxAmountError).setCancelable(false)
//                        .setPositiveButton(R.string.ok
//                        ) { _, _ -> }
//                    a_builder.show()
//                }
//                else{
//                    viewModel.saveMinAmount(currentMinAmount)
//                }
//            }
//        })

//        val maxSeekBar: SeekBar = fragmentView.findViewById(R.id.maxPhotoAmount)
//        maxSeekBar.progress = currentMaxAmount


//        maxSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                currentMaxAmount = maxSeekBar.progress
//                maxTextView.text = currentMaxAmount.toString()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                if (currentMaxAmount < currentMinAmount) {
//                    maxSeekBar.progress = viewModel.getMaxPrefs()
//                    //todo вынеси AlertDialog в отдельный класс (отнаследуйся от него)
//                    val a_builder = AlertDialog.Builder(context!!)
//                    a_builder.setMessage(R.string.maxAmountError).setCancelable(false)
//                        .setPositiveButton(R.string.ok
//                        ) { _, _ -> }
//                    a_builder.show()
//                }
//                else{
//                    viewModel.saveMaxAmount(currentMaxAmount)
//                }
//            }
//        })

        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeState(viewLifecycleOwner){state ->
            //todo отображение state
            //будь внимателен при вставке нового прогресса в seek bar
            //слушатель опять тригернется, стейт обновится и так до бесконечности
            //есть два варианта: либо его тут убирать, вставлять значение, и опять добавлять слушатель
            //либо можно еще в классе SeekBarListener в методе onProgressChanged посмотреть на fromUser
            //скорее всего он будет false, если значение было вставлено программно, я этим не занимался
        }
    }

}




