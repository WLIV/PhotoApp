package com.example.photoapp.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.photoapp.R


class MinMaxAlertDialog(context: Context, val message : String, val title : String) : AlertDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)
        val messageTV = findViewById<TextView>(R.id.message)
        messageTV.text = message
        setCancelable(true)
        val okButton : Button = findViewById(R.id.dialog_button)
        okButton.setOnClickListener { dismiss() }
        val titleTV : TextView = findViewById(R.id.dialog_title)
        titleTV.text = title
    }
}