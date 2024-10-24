package com.cc17.progrifit30.screens.extensiveworkout.StrengthTraining

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.extensiveworkout.SelectTraining

class StrengthTraining: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_strengthtraining)

        val workoutType = getSharedPreferences("workout_type", Context.MODE_PRIVATE)
        val workoutLocation = getSharedPreferences("workout_location", Context.MODE_PRIVATE)

        val workoutVal = workoutType.getString("workout_type", "").toString()

        val header = findViewById<TextView>(R.id.headerText)
        header.text = workoutVal

        val intent = Intent(this@StrengthTraining, SelectTraining::class.java)

        val upperButton = findViewById<Button>(R.id.ext_strength_upper_button)
        upperButton.setOnClickListener {
            workoutLocation.edit().putString("workout_location", upperButton.text.toString()).apply()
            startActivity(intent)
        }

        val lowerButton = findViewById<Button>(R.id.ext_strength_lower_button)
        lowerButton.setOnClickListener {
            workoutLocation.edit().putString("workout_location", lowerButton.text.toString()).apply()
            startActivity(intent)
        }

        val coreButton = findViewById<Button>(R.id.ext_strength_core_button)
        coreButton.setOnClickListener {
            workoutLocation.edit().putString("workout_location", coreButton.text.toString()).apply()
            startActivity(intent)
        }
    }
}
