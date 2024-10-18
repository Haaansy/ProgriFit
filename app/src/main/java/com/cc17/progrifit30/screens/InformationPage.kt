package com.cc17.progrifit30.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cc17.progrifit30.R
import com.cc17.progrifit30.db.model.User
import com.cc17.progrifit30.db.userDB.UserViewModel

class InformationPage : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_information)

        // User View Model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // SharedPreferences
        sharedPref = getSharedPreferences("user_name", Context.MODE_PRIVATE)

        if (wasUserSaved()) {
            val intent = Intent(this@InformationPage, HomePage::class.java)
            startActivity(intent)
            finish()
        }

        // Next Button Click Listener
        val nextButton = findViewById<Button>(R.id.information_button_next)
        nextButton.setOnClickListener {
            addToDataBase()
        }
    }

    // Function to get Values from EditText and Call Check for input before adding to database
    private fun addToDataBase() {
        val name = findViewById<EditText>(R.id.name_input).text.toString()
        val age = findViewById<EditText>(R.id.age_input).text
        val weight = findViewById<EditText>(R.id.weight_input).text
        val height = findViewById<EditText>(R.id.height_input).text // New height input
        val gender = findViewById<EditText>(R.id.gender_input).text.toString() // New gender input
        val activityLevel = findViewById<EditText>(R.id.activity_level_input).text.toString() // New activity level input
        val duration = findViewById<EditText>(R.id.duration_input).text

        if (inputCheck(name, age, weight, height, gender, activityLevel, duration)) {
            // Calculate TDEE
            val tdee = calculateTDEE(weight.toString().toDouble(), height.toString().toDouble(), age.toString().toInt(), gender, activityLevel)

            // Create User Object
            val user = User(0, name, age.toString().toInt(), weight.toString().toDouble(), tdee, duration.toString().toInt())

            // Add Data to Database
            mUserViewModel.insertUser(user)

            // Add user to sharedPref
            sharedPref.edit().putString("user_name", name).apply()
            Toast.makeText(this, "Registration Success!", Toast.LENGTH_LONG).show()

            // Go to Next Activity after insertion of data to database
            val intent = Intent(this@InformationPage, HomePage::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Please fill up all fields.", Toast.LENGTH_LONG).show()
        }
    }

    // Check Input Function [ CHECK EMPTY FIELDS ]
    private fun inputCheck(name: String, age: Editable, weight: Editable, height: Editable, gender: String, activityLevel: String, duration: Editable): Boolean {
        return !(TextUtils.isEmpty(name) || age.isEmpty() || weight.isEmpty() || height.isEmpty() || gender.isEmpty() || activityLevel.isEmpty() || duration.isEmpty())
    }

    // Calculate TDEE
    private fun calculateTDEE(weight: Double, height: Double, age: Int, gender: String, activityLevel: String): Double {
        val bmr: Double = when (gender.lowercase()) {
            "male" -> 10 * weight + 6.25 * height - 5 * age + 5
            "female" -> 10 * weight + 6.25 * height - 5 * age - 161
            else -> 0.0 // Handle invalid gender input
        }

        val activityMultiplier = when (activityLevel.lowercase()) {
            "sedentary" -> 1.2
            "light" -> 1.375
            "moderate" -> 1.55
            "active" -> 1.725
            "very active" -> 1.9
            else -> 1.0 // Default multiplier
        }

        return bmr * activityMultiplier
    }

    // Check if User is saved in the SharedPreferences
    private fun wasUserSaved(): Boolean {
        return sharedPref.getString("user_name", "")!!.isNotEmpty()
    }
}