package com.example.photoapp.utils

import android.widget.SeekBar
import kotlin.properties.Delegates

//можешь его сделать анонимным или вложенным по своему усмотрению
class SeekBarListener(
    private val onProgressChanged: (progress: Int) -> Unit
): SeekBar.OnSeekBarChangeListener {
private var progress by Delegates.notNull<Int>()

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
       this.progress = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) { onProgressChanged(progress)}

}