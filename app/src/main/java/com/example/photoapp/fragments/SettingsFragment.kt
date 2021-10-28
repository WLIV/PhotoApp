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
import com.example.photoapp.utils.MinMaxAlertDialog
import com.example.photoapp.utils.SeekBarListener
import com.example.photoapp.viewmodels.SettingsFragmentViewModel


class SettingsFragment : Fragment() {

    //todo старайся все поля делать nullable, а не lateinit. Иначе очень легко выстрелить себе в ногу,
    // обратившись к полю до инициализации. Почитай про делегат lazy в котлине, очень полезная вещь
    private lateinit var fragmentView: View
    private lateinit var maxTextView : TextView
    private lateinit var minTextView: TextView
    private lateinit var minSeekBar : SeekBar
    private lateinit var maxSeekBar : SeekBar
    //получаем вьюмодель с помощью делешата (мажешь почитать об этом, если интересно
    private val viewModel: SettingsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_settings, container, false)



        minTextView = fragmentView.findViewById(R.id.chosenAmountMin)


         maxTextView = fragmentView.findViewById(R.id.chosenAmountMax)


        minSeekBar = fragmentView.findViewById(R.id.minPhotoAmount)


        minSeekBar.setOnSeekBarChangeListener(SeekBarListener {
            //тут просто говорим вьюмодели, что данные поменялись
            //todo обрати внимание на коммент выше, мы не должны тут сеттить строку во вью.
            //все данные сеттятся у нас в методе observeState, ты как раз там это и делаешь
            minTextView.text = it.toString()
            viewModel.onMinAmountChanged(it)
        })

        maxSeekBar  = fragmentView.findViewById(R.id.maxPhotoAmount)

        maxSeekBar.setOnSeekBarChangeListener(SeekBarListener{
            //та же проблема что и выше
            maxTextView.text= it.toString()
            viewModel.onMaxAmountChanged(it)
        })

        viewModel.observeAlertDialog(viewLifecycleOwner, {
            if (it != null) {
                val dialog =
                    MinMaxAlertDialog(requireContext(), it, getString(R.string.minMaxErrorTitle))
                dialog.show()
            }
        })



        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeState(viewLifecycleOwner){state ->

            //будь внимателен при вставке нового прогресса в seek bar
            //слушатель опять тригернется, стейт обновится и так до бесконечности
            //есть два варианта: либо его тут убирать, вставлять значение, и опять добавлять слушатель
            //либо можно еще в классе SeekBarListener в методе onProgressChanged посмотреть на fromUser
            //скорее всего он будет false, если значение было вставлено программно, я этим не занимался
            maxSeekBar.progress = state.maxPhotosAmount
            maxTextView.text = state.maxPhotosAmount.toString()
            minSeekBar.progress = state.minPhotosAmount
            minTextView.text = state.minPhotosAmount.toString()

        }
    }

}




