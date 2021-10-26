package com.example.photoapp.utils

import android.widget.SeekBar
import kotlin.properties.Delegates

//можешь его сделать анонимным или вложенным по своему усмотрению
class SeekBarListener(
    private val onProgressChanged: (progress: Int) -> Unit
): SeekBar.OnSeekBarChangeListener {


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        if (seekBar != null) {
            onProgressChanged(seekBar.progress)
        }
    }

}