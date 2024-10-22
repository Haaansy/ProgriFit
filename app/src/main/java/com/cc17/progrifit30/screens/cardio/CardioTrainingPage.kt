package com.cc17.progrifit30.screens.cardio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cc17.progrifit30.R

class CardioTrainingPage: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cardio)

        val addActionButton = findViewById<Button>(R.id.actionButton_add)
        addActionButton.setOnClickListener {
            val intent = Intent(this@CardioTrainingPage, addCardioRoute::class.java)
            startActivity(intent)
        }
    }
}