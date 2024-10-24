package com.cc17.progrifit30.screens.extensiveworkout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.extensiveworkout.StrengthTraining.StrengthExercises

class SelectTraining: AppCompatActivity() {

    private lateinit var workoutName: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ext_select_training)

        val workoutType = getSharedPreferences("workout_type", Context.MODE_PRIVATE)
        val workoutLocation = getSharedPreferences("workout_location", Context.MODE_PRIVATE)
        workoutName = getSharedPreferences("workout_name", Context.MODE_PRIVATE)

        val workoutVal = workoutType.getString("workout_type", "").toString()
        val workoutLoc = workoutLocation.getString("workout_location", "").toString()

        val headerText = findViewById<TextView>(R.id.workout_type_text2)
        val subheaderText = findViewById<TextView>(R.id.workout_location_text2)
        headerText.text = workoutVal
        subheaderText.text = workoutLoc

        if(workoutVal == "Strength Training") {
            if(workoutLoc == "Upper Body") {
                val exerciseList = StrengthExercises.getUpperBodyExerciseNames()
                createButtonList(exerciseList)
            }else if(workoutLoc == "Lower Body") {
                val exerciseList = StrengthExercises.getLowerBodyExerciseNames()
                createButtonList(exerciseList)
            }else if(workoutLoc == "Core") {
                val exerciseList = StrengthExercises.getCoreExerciseNames()
                createButtonList(exerciseList)
            }
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
                workoutName.edit().putString("workout_name", button.text.toString()).apply()
                val intent = Intent(this@SelectTraining, StartTraining::class.java)
                startActivity(intent)
            }

            // Add the button to the layout
            buttonContainer.addView(button)
        }
    }
}
