package com.cc17.progrifit30.screens.extensiveworkout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.cc17.progrifit30.R
import com.cc17.progrifit30.screens.extensiveworkout.StrengthTraining.StrengthExercises

class StartTraining: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ext_start_training)

        val workoutType = getSharedPreferences("workout_type", Context.MODE_PRIVATE)
        val workoutLocation = getSharedPreferences("workout_location", Context.MODE_PRIVATE)
        val workoutName = getSharedPreferences("workout_name", Context.MODE_PRIVATE)

        val workoutVal = workoutType.getString("workout_type", "").toString()
        val workoutLoc = workoutLocation.getString("workout_location", "").toString()
        val workoutExerciseName = workoutName.getString("workout_name", "").toString()

        val headerText = findViewById<TextView>(R.id.workout_type_text)
        val subheaderText = findViewById<TextView>(R.id.workout_location_text)
        headerText.text = workoutVal
        subheaderText.text = workoutLoc

        val exerciseInfo = StrengthExercises.getExercise(workoutLoc, workoutExerciseName)

        // Unpack the returned array
        if (exerciseInfo != null) {
            val name = exerciseInfo[0]
            val duration = exerciseInfo[1]
            val repetitions = exerciseInfo[2]

            // Print or use the exercise data
            println("Exercise Name: $name")
            println("Duration: $duration seconds")
            println("Repetitions: $repetitions")
        }

    }
}
