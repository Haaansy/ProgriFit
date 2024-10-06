package com.cc17.progrifit30.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cc17.progrifit30.R

class LandingPage: AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)

        sharedPref = getSharedPreferences("user_name", Context.MODE_PRIVATE)

        if(wasUserSaved()){
            val intent = Intent(this@LandingPage, HomePage::class.java)
            startActivity(intent)
            finish()
        }

        val nextButton = findViewById<Button>(R.id.landing_button_next)
        nextButton.setOnClickListener {
            val intent = Intent(this@LandingPage, InformationPage::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Check if User is saved in the SharedPreferences
    private fun wasUserSaved():Boolean{
        return sharedPref.getString("user_name", "")!!.isNotEmpty()
    }
}
