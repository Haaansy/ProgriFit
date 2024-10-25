package com.cc17.progrifit30.screens.resistanceWorkout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cc17.progrifit30.R

class ResistanceWorkoutPage: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resistanceworkout)

        sharedPreferences = getSharedPreferences("workout_type", Context.MODE_PRIVATE)

        val upperbodyButton = findViewById<Button>(R.id.res_upper_body_button)
        upperbodyButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", upperbodyButton.text.toString()).apply()
            Toast.makeText(this, upperbodyButton.text.toString(), Toast.LENGTH_LONG).show()

            val intent = Intent(this@ResistanceWorkoutPage, SelectTraining::class.java)
            startActivity(intent)
        }

        val lowerbodyButton = findViewById<Button>(R.id.res_lower_body_button)
        lowerbodyButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", lowerbodyButton.text.toString()).apply()
            Toast.makeText(this, lowerbodyButton.text.toString(), Toast.LENGTH_LONG).show()

            val intent = Intent(this@ResistanceWorkoutPage, SelectTraining::class.java)
            startActivity(intent)
        }

        val coreButton = findViewById<Button>(R.id.res_core_button)
        coreButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", coreButton.text.toString()).apply()
            Toast.makeText(this, coreButton.text.toString(), Toast.LENGTH_LONG).show()

            val intent = Intent(this@ResistanceWorkoutPage, SelectExercises::class.java)
            startActivity(intent)
        }

        val fullbodyButton = findViewById<Button>(R.id.res_full_button)
        fullbodyButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", fullbodyButton.text.toString()).apply()
            Toast.makeText(this, fullbodyButton.text.toString(), Toast.LENGTH_LONG).show()

            val intent = Intent(this@ResistanceWorkoutPage, SelectExercises::class.java)
            startActivity(intent)
        }

        val bodyweightButton = findViewById<Button>(R.id.res_body_weight_button)
        bodyweightButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", bodyweightButton.text.toString()).apply()
            Toast.makeText(this, bodyweightButton.text.toString(), Toast.LENGTH_LONG).show()

            val intent = Intent(this@ResistanceWorkoutPage, SelectExercises::class.java)
            startActivity(intent)
        }
    }
}