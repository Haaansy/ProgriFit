package com.cc17.progrifit30.screens.cardio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.HomePage

class StartRun: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_run)

        val stopButton = findViewById<Button>(R.id.end_route_button)
        stopButton.setOnClickListener {
            val intent = Intent(this@StartRun, HomePage::class.java)
            startActivity(intent)
        }
    }
}
