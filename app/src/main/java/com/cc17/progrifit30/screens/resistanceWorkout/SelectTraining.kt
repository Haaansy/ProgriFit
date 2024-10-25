package com.cc17.progrifit30.screens.resistanceWorkout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.resistanceWorkout.workoutLocation.ExerciseLocation

class SelectTraining: AppCompatActivity() {

    private lateinit var workoutLocation: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_res_select_training)

        val workoutType = getSharedPreferences("workout_type", Context.MODE_PRIVATE)
        workoutLocation = getSharedPreferences("workout_location", Context.MODE_PRIVATE)

        val workoutVal = workoutType.getString("workout_type", "").toString()

        val headerText = findViewById<TextView>(R.id.workout_type_text3)
        headerText.text = workoutVal

        if(workoutVal == "Upper-Body") {
            val exerciseLocationList = ExerciseLocation.getUpperBodyResistanceExercises()
            createButtonList(exerciseLocationList)
        } else if(workoutVal == "Lower-Body") {
            val exerciseLocationList = ExerciseLocation.getLowerBodyResistanceExercises()
            createButtonList(exerciseLocationList)
        }
    }

    private fun createButtonList(exerciseList: List<String>){
        val buttonContainer = findViewById<LinearLayout>(R.id.button_container)

        for (exercise in exerciseList) {
            // Create a new Button
            val button = Button(this)

            // Set the button text to the exercise name
            button.text = exercise

            // Set button layout parameters (optional)
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // width
                ViewGroup.LayoutParams.WRAP_CONTENT  // height
            )
            layoutParams.setMargins(16, 16, 16, 16) // optional: set margins
            button.layoutParams = layoutParams
            button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Secondary)
            button.background = ContextCompat.getDrawable(this, R.drawable.rounded_button)

            // Set the text color to a color resource (e.g., White)
            button.setTextColor(ContextCompat.getColor(this, R.color.White))

            // Optionally: Set an onClickListener for each button
            button.setOnClickListener {
                // Handle button click
                finish()
                workoutLocation.edit().putString("workout_location", button.text.toString()).apply()
                val intent = Intent(this@SelectTraining, SelectExercises::class.java)
                startActivity(intent)
            }

            // Add the button to the layout
            buttonContainer.addView(button)
        }
    }
}