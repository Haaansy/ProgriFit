package com.cc17.progrifit30.screens.extensiveworkout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.extensiveworkout.StrengthTraining.StrengthTraining

class ExtensiveWorkoutPage: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_extensiveworkout)

        sharedPreferences = getSharedPreferences("workout_type", Context.MODE_PRIVATE)



        val strengthButton = findViewById<Button>(R.id.ext_strength_button)
        strengthButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", strengthButton.text.toString()).apply()
            Toast.makeText(this, strengthButton.text.toString(), Toast.LENGTH_LONG).show()

            val intent = Intent(this@ExtensiveWorkoutPage, StrengthTraining::class.java)
            startActivity(intent)
        }

        val cardioButton = findViewById<Button>(R.id.ext_cardio_button)
        cardioButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", cardioButton.text.toString()).apply()
            Toast.makeText(this, strengthButton.text.toString(), Toast.LENGTH_LONG).show()
        }

        val flexButton = findViewById<Button>(R.id.ext_flex_button)
        flexButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", flexButton.text.toString()).apply()
            Toast.makeText(this, strengthButton.text.toString(), Toast.LENGTH_LONG).show()
        }

        val fullButton = findViewById<Button>(R.id.ext_full_button)
        fullButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", fullButton.text.toString()).apply()
            Toast.makeText(this, strengthButton.text.toString(), Toast.LENGTH_LONG).show()
        }

        val bodyButton = findViewById<Button>(R.id.ext_body_weight_button)
        bodyButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", bodyButton.text.toString()).apply()
            Toast.makeText(this, strengthButton.text.toString(), Toast.LENGTH_LONG).show()
        }

        val pyloButton = findViewById<Button>(R.id.ext_pylo_button)
        pyloButton.setOnClickListener {
            sharedPreferences.edit().putString("workout_type", pyloButton.text.toString()).apply()
            Toast.makeText(this, strengthButton.text.toString(), Toast.LENGTH_LONG).show()
        }
    }
}