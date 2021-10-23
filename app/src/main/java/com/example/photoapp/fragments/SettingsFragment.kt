package com.example.photoapp.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.photoapp.R
import com.example.photoapp.viewmodels.NewsFragmentViewModel
import com.example.photoapp.viewmodels.SettingsFragmentViewModel


class SettingsFragment : Fragment() {

    private lateinit var fragmentView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_settings, container, false)
        val viewModel: SettingsFragmentViewModel =
            ViewModelProvider(this)[SettingsFragmentViewModel::class.java]
        viewModel.getPrefs(requireContext())
        var currentMinAmount: Int = viewModel.getMinPrefs()
        var currentMaxAmount: Int = viewModel.getMaxPrefs()

        val minTextView = fragmentView.findViewById<TextView>(R.id.chosenAmountMin)
        minTextView.text = currentMinAmount.toString()

        val maxTextView = fragmentView.findViewById<TextView>(R.id.chosenAmountMax)
        maxTextView.text = currentMaxAmount.toString()

        val minSeekBar: SeekBar = fragmentView.findViewById(R.id.minPhotoAmount)
        minSeekBar.progress = currentMinAmount

        minSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                currentMinAmount = minSeekBar.progress
                minTextView.text = currentMinAmount.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (currentMaxAmount < currentMinAmount) {
                    minSeekBar.progress = viewModel.getMinPrefs()
                    val a_builder = AlertDialog.Builder(context!!)
                    a_builder.setMessage(R.string.maxAmountError).setCancelable(false)
                        .setPositiveButton(R.string.ok
                        ) { _, _ -> }
                    a_builder.show()
                }
                else{
                    viewModel.saveMinAmount(currentMinAmount)
                }
            }
        })

        val maxSeekBar: SeekBar = fragmentView.findViewById(R.id.maxPhotoAmount)
        maxSeekBar.progress = currentMaxAmount

        maxSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentMaxAmount = maxSeekBar.progress
                maxTextView.text = currentMaxAmount.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (currentMaxAmount < currentMinAmount) {
                    maxSeekBar.progress = viewModel.getMaxPrefs()
                    val a_builder = AlertDialog.Builder(context!!)
                    a_builder.setMessage(R.string.maxAmountError).setCancelable(false)
                        .setPositiveButton(R.string.ok
                        ) { _, _ -> }
                    a_builder.show()
                }
                else{
                    println(currentMaxAmount)
                    viewModel.saveMaxAmount(currentMaxAmount)
                }
            }
        })

        return fragmentView
    }

}




