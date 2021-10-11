package com.example.photoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Lifecycle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val photoBtn = findViewById<Button>(R.id.photoBtn)
        photoBtn.setOnClickListener {
            val intent = Intent(this, PhotoActivity::class.java)
            startActivity(intent)
        }
    }
}