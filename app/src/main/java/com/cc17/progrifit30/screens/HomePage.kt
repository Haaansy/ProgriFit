package com.cc17.progrifit30.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cc17.progrifit30.R
import com.cc17.progrifit30.db.userDB.UserViewModel
import com.cc17.progrifit30.screens.cardio.CardioTrainingPage

class HomePage: AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)

        val nameLabel = findViewById<TextView>(R.id.username)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer{user ->
            "Hello, ${user.name}!".also { nameLabel.text = it }
        })


        val cardioButton = findViewById<Button>(R.id.homepage_cardio_button)
        cardioButton.setOnClickListener {
            val intent = Intent(this@HomePage, CardioTrainingPage::class.java)
            startActivity(intent)
        }

        val resistanceButton = findViewById<Button>(R.id.homepage_resistance_button)
        resistanceButton.setOnClickListener {
            /* val intent = Intent(this@HomePage, HomeWorkout::class.java)
            startActivity(intent) */
        }

        val extensiveWorkoutButton = findViewById<Button>(R.id.homepage_extensive_button)
        extensiveWorkoutButton.setOnClickListener {
            /* val intent = Intent(this@HomePage, ExtensiveWorkout::class.java)
            startActivity(intent) */
        }
    }
}