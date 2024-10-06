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

class HomePage: AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)

        val nameLabel = findViewById<TextView>(R.id.username)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer{user ->
            nameLabel.text = user.name
        })


        val cardioButton = findViewById<Button>(R.id.homepage_cardio_button)
        cardioButton.setOnClickListener {
            val intent = Intent(this@HomePage, SetCardioTraining::class.java)
            startActivity(intent)
        }
    }


}