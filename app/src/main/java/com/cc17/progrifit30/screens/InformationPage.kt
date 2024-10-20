package com.cc17.progrifit30.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.cc17.progrifit30.R
import com.cc17.progrifit30.db.model.User
import com.cc17.progrifit30.db.userDB.UserViewModel
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class InformationPage : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var sharedPref: SharedPreferences

    companion object {
        private var activityLevel = ""
        private var gender = ""

        fun modifyActivity(value: String){
            activityLevel = value
        }

        fun getActivity(): String {
            return this.activityLevel
        }

        fun modifyGender(value: String){
            gender = value
        }

        fun getGender(): String {
            return this.gender
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_information)

        // Drop Down Items for Activity Level
        val activityItems = listOf("Sedentary","Light","Moderate","Active","Very Active" )

        val activityAutocomplete : AutoCompleteTextView = findViewById(R.id.autocomplete_activity_level)

        val activityAdapter = ArrayAdapter(this, R.layout.list_item, activityItems)

        activityAutocomplete.setAdapter(activityAdapter)

        activityAutocomplete.onItemClickListener = AdapterView.OnItemClickListener {
                AdapterView, view, position, id ->
            val itemSelected = AdapterView.getItemAtPosition(position)
            val companion = Companion
            companion.modifyActivity(itemSelected.toString())
        }

        // Drop Down Items for Gender
        val genderItems = listOf("Male","Female")

        val genderAutocomplete : AutoCompleteTextView = findViewById(R.id.autocomplete_gender)

        val genderAdapter = ArrayAdapter(this, R.layout.list_item, genderItems)

        genderAutocomplete.setAdapter(genderAdapter)

        genderAutocomplete.onItemClickListener = AdapterView.OnItemClickListener {
                AdapterView, view, position, id ->
            val itemSelected = AdapterView.getItemAtPosition(position)
            val companion = Companion
            companion.modifyGender(itemSelected.toString())
        }

        // User View Model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // SharedPreferences
        sharedPref = getSharedPreferences("user_name", Context.MODE_PRIVATE)

        // Check if User is saved to local database
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
        val name = findViewById<TextInputLayout>(R.id.name_input).editText?.text.toString()
        val age = findViewById<TextInputLayout>(R.id.age_input).editText?.text.toString()
        val weight = findViewById<TextInputLayout>(R.id.weight_input).editText?.text.toString()
        val height = findViewById<TextInputLayout>(R.id.height_input).editText?.text.toString()

        // Companion Object
        val companion = Companion
        val activityLevel = companion.getActivity()
        val gender = companion.getGender()

        if (inputCheck(name, age, weight, height, activityLevel, gender)) {
            // Calculate TDEE
            val tdee = calculateTDEE(weight.toDouble(), height.toDouble(), age.toInt(), gender, activityLevel)

            // Create User Object
            val user = User(0, name, age.toInt(), weight.toDouble(), height.toInt(), tdee, gender, activityLevel)

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
    private fun inputCheck(name: String, age: String, weight: String, height: String, activityLevel: String, gender: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(weight) || TextUtils.isEmpty(height) ||  activityLevel.isEmpty() || gender.isEmpty() )
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