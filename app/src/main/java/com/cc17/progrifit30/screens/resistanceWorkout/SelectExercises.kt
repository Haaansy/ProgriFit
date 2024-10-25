package com.cc17.progrifit30.screens.resistanceWorkout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.CaseMap.Upper
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.resistanceWorkout.Exercises.BodyWeightExercises
import com.cc17.progrifit30.screens.resistanceWorkout.Exercises.CoreExercises
import com.cc17.progrifit30.screens.resistanceWorkout.Exercises.FullBodyExercises
import com.cc17.progrifit30.screens.resistanceWorkout.Exercises.LowerBodyExercises
import com.cc17.progrifit30.screens.resistanceWorkout.Exercises.UpperBodyExercises

class SelectExercises: AppCompatActivity() {

    private lateinit var workoutName: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_res_select_exercises)

        val workoutType = getSharedPreferences("workout_type", Context.MODE_PRIVATE)
        val workoutLocation = getSharedPreferences("workout_location", Context.MODE_PRIVATE)
        workoutName = getSharedPreferences("workout_name", Context.MODE_PRIVATE)

        val workoutVal = workoutType.getString("workout_type", "").toString()
        val workoutLoc = workoutLocation.getString("workout_location", "").toString()

        val headerText = findViewById<TextView>(R.id.workout_type_text3)
        headerText.text = workoutVal
        val subheaderText = findViewById<TextView>(R.id.workout_location_text3)

        if(workoutVal == "Upper-Body") {
            subheaderText.text = workoutLoc
            if(workoutLoc == "Chest") {
                val exerciseList = UpperBodyExercises.getChestExercises()
                createButtonList(exerciseList)
            } else if(workoutLoc == "Back") {
                val exerciseList = UpperBodyExercises.getBackExercises()
                createButtonList(exerciseList)
            } else if(workoutLoc == "Shoulders") {
                val exerciseList = UpperBodyExercises.getShouldersExercises()
                createButtonList(exerciseList)
            } else if(workoutLoc == "Arms") {
                val exerciseList = UpperBodyExercises.getArmsExercises()
                createButtonList(exerciseList)
            }
        } else if(workoutVal == "Lower-Body") {
            subheaderText.text = workoutLoc
            if(workoutLoc == "Quads and Hamstrings") {
                val exerciseList = LowerBodyExercises.getQuadsExercise()
                createButtonList(exerciseList)
            } else if(workoutLoc == "Glutes") {
                val exerciseList = LowerBodyExercises.getGlutesExercise()
                createButtonList(exerciseList)
            } else if(workoutLoc == "Calves") {
                val exerciseList = LowerBodyExercises.getCalvesExercise()
                createButtonList(exerciseList)
            }
        } else if(workoutVal == "Core") {
            subheaderText.text = ""
            val exerciseList = CoreExercises.getCoreExercises()
            createButtonList(exerciseList)
        } else if(workoutVal == "Full-Body") {
            subheaderText.text = ""
            val exerciseList = FullBodyExercises.getFullBodyExercises()
            createButtonList(exerciseList)
        } else if(workoutVal == "Body Weight Exercises") {
            subheaderText.text = ""
            val exerciseList = BodyWeightExercises.getBodyWeightExercises()
            createButtonList(exerciseList)
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
                workoutName.edit().putString("workout_name", button.text.toString()).apply()
                val intent = Intent(this@SelectExercises, StartTraining::class.java)
                startActivity(intent)
            }

            // Add the button to the layout
            buttonContainer.addView(button)
        }
    }
}